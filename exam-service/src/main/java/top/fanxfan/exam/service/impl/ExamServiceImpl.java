package top.fanxfan.exam.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import top.fanxfan.core.exception.ServiceException;
import top.fanxfan.core.tools.PageUtils;
import top.fanxfan.exam.entity.*;
import top.fanxfan.exam.enums.PartModel;
import top.fanxfan.exam.repository.*;
import top.fanxfan.exam.service.ExamService;
import top.fanxfan.exam.vo.ExamPartVo;
import top.fanxfan.exam.vo.ExamQuestionVo;
import top.fanxfan.exam.vo.ExamSearch;
import top.fanxfan.exam.vo.ExamVo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static top.fanxfan.exam.contants.ExamErrorConstants.EXAM_NOT_FOUND;
import static top.fanxfan.exam.contants.ExamErrorConstants.PART_MODEL_ERROR;

/**
 * 考试Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    private final QuestionRepository questionRepository;

    private final QuestionCatalogRepository questionCatalogRepository;

    private final ExamPartRepository examPartRepository;

    private final ExamQuestionRepository examQuestionRepository;

    private final UserExamRepository userExamRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Exam> list(ExamSearch examSearch) {
        QExam exam = QExam.exam;
        BooleanBuilder builder = new BooleanBuilder();
        if (ObjectUtil.isNotEmpty(examSearch.getKeyword())) {
            builder.and(exam.name.endsWith(examSearch.getKeyword()));
        }
        if (ObjectUtil.isNotEmpty(examSearch.getStartTime()) && ObjectUtil.isNotEmpty(examSearch.getEndTime())) {
            builder.and(exam.startTime.between(examSearch.getStartTime(), examSearch.getEndTime()));
        }
        return examRepository.findAll(builder, PageUtils.page(examSearch));

    }

    @Override
    public Exam get(Long id) {
        return examRepository.findById(id).orElseThrow(() -> new ServiceException(EXAM_NOT_FOUND));
    }

    @Override
    public Boolean remove(Long id) {
        // 直接删除考试
        examRepository.findById(id).ifPresent(examRepository::delete);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean createWithAll(ExamVo examVo) {
        Exam build = Exam.builder()
                .startTime(examVo.getStartTime())
                .endTime(examVo.getEndTime())
                .name(examVo.getName())
                .time(examVo.getTime())
                .timeMode(examVo.getTimeMode())
                .build();
        examRepository.saveAndFlush(build);
        // 考试试题处理
        Stream<ExamPart> partStream = examVo.getParts().stream().map(part -> {
                    // 构建当前部分的考试试题
                    List<ExamQuestion> questionStreamList = getExamQuestions(part, build);
                    // 保存
                    examQuestionRepository.saveAllAndFlush(questionStreamList);
                    // 构建examPart对象
                    return ExamPart.builder()
                            .exam(build)
                            .name(part.getName())
                            .model(part.getModel())
                            .orderNum(part.getOrderNum())
                            .questions(questionStreamList)
                            .build();
                }
        );
        List<ExamPart> parts = partStream.toList();
        examPartRepository.saveAllAndFlush(parts);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Exam createOnlyBase(ExamVo examVo) {
        Exam build = Exam.builder()
                .startTime(examVo.getStartTime())
                .endTime(examVo.getEndTime())
                .name(examVo.getName())
                .time(examVo.getTime())
                .timeMode(examVo.getTimeMode())
                .build();
        return examRepository.saveAndFlush(build);
    }

    @Override
    public Boolean updateOnly(ExamVo examVo) {
        Exam exam = examRepository.findById(examVo.getId()).orElseThrow(() -> new ServiceException(EXAM_NOT_FOUND));
        BeanUtils.copyProperties(examVo, exam, "id", "createTime");
        examRepository.saveAndFlush(exam);
        return true;
    }

    /**
     * 考试试题处理
     *
     * @param part 当前部分
     * @param exam 考试
     * @return 考试试题列表
     */
    private List<ExamQuestion> getExamQuestions(ExamPartVo part, Exam exam) {
        List<ExamQuestion> questionStreamList;
        if (part.getModel().equals(PartModel.SELECTION_MODE)) {
            // 获取当前部分的所有试题vo
            List<ExamQuestionVo> questions = part.getQuestions();
            // 获取试题id
            List<Long> questionsIds = questions.stream().map(ExamQuestionVo::getQuestionId).toList();
            // 获取试题
            List<Question> list = questionRepository.findAllById(questionsIds).stream()
                    .filter(Objects::nonNull)
                    .toList();
            // 根据question的id转换为map集合
            Map<Long, Question> questionMap = list.stream()
                    .collect(Collectors.toMap(Question::getId, Function.identity()));
            // 转换为examQuestion对象
            Stream<ExamQuestion> examQuestionStream = questions.stream()
                    .filter(Objects::nonNull)
                    .filter(obj -> questionMap.containsKey(obj.getQuestionId()))
                    .map(obj -> ExamQuestion.builder()
                            .exam(exam)
                            .score(obj.getScore())
                            .question(questionMap.getOrDefault(obj.getQuestionId(), null))
                            .build()
                    );
            // 过滤掉为空的question
            questionStreamList = examQuestionStream
                    .filter(obj -> ObjectUtil.isNotEmpty(obj.getQuestion()))
                    .toList();
        } else if (part.getModel().equals(PartModel.STOCHASTIC_MODE)) {
            Integer itemScore = part.getItemScore();
            // 获取当前部分的所有试题vo
            List<Question> byType = questionRepository.findByType(part.getQuestionType());
            // 随机抽取试题
            Collections.shuffle(byType);
            byType = byType.subList(0, part.getTotalSum());
            // 转换为examQuestion对象
            Stream<ExamQuestion> examQuestionStream = byType.stream()
                    .filter(Objects::nonNull)
                    .map(questionObj -> ExamQuestion.builder()
                            .exam(exam)
                            .question(questionObj)
                            .score(itemScore)
                            .build());
            questionStreamList = examQuestionStream.toList();
        } else {
            throw new ServiceException(PART_MODEL_ERROR);
        }
        return questionStreamList;
    }
}
