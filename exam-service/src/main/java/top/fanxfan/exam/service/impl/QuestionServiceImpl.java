package top.fanxfan.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import top.fanxfan.core.tools.PageUtils;
import top.fanxfan.exam.entity.QQuestion;
import top.fanxfan.exam.entity.Question;
import top.fanxfan.exam.entity.QuestionOption;
import top.fanxfan.exam.repository.QuestionCatalogRepository;
import top.fanxfan.exam.repository.QuestionOptionRepository;
import top.fanxfan.exam.repository.QuestionRepository;
import top.fanxfan.exam.service.QuestionService;
import top.fanxfan.exam.vo.QuestionSearchVo;
import top.fanxfan.exam.vo.QuestionVo;

import java.util.List;
import java.util.stream.Stream;

import static top.fanxfan.exam.contants.ExamErrorConstants.QUESTION_NOT_FOUND;

/**
 * 试题Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final QuestionCatalogRepository questionCatalogRepository;

    private final QuestionOptionRepository questionOptionRepository;

    @Override
    public Page<Question> list(QuestionSearchVo questionSearchVo) {
        QQuestion question = QQuestion.question;
        BooleanBuilder builder = new BooleanBuilder();
        if (ObjectUtil.isNotEmpty(questionSearchVo.getKeyword())) {
            builder.and(question.title.contains(questionSearchVo.getKeyword()));
        }
        if (ObjectUtil.isNotEmpty(questionSearchVo.getType())) {
            builder.and(question.type.eq(questionSearchVo.getType()));
        }
        if (ObjectUtil.isNotEmpty(questionSearchVo.getDegree())) {
            builder.and(question.degree.eq(questionSearchVo.getDegree()));
        }
        if (ObjectUtil.isNotEmpty(questionSearchVo.getCatalogId())) {
            builder.and(question.questionCatalog.id.eq(questionSearchVo.getCatalogId()));
        }
        return questionRepository.findAll(builder, PageUtils.page(questionSearchVo));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean add(@NotNull QuestionVo questionVo) {
        Question question = Question.builder()
                .title(questionVo.getTitle())
                .type(questionVo.getType())
                .degree(questionVo.getDegree())
                .analysis(questionVo.getAnalysis())
                .answer(questionVo.getAnswer())
                .build();

        dealOption(questionVo, question);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean remove(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException(QUESTION_NOT_FOUND));
        questionRepository.delete(question);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean update(QuestionVo questionVo) {
        Question question = questionRepository.findById(questionVo.getId()).orElseThrow(() -> new RuntimeException(QUESTION_NOT_FOUND));
        BeanUtil.copyProperties(questionVo, question, "options");
        questionOptionRepository.deleteAll(question.getOptions());
        dealOption(questionVo, question);
        return true;
    }

    @Override
    public Question get(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException(QUESTION_NOT_FOUND));
    }

    /**
     * 试题处理
     *
     * @param questionVo 试题
     * @param question   试题实体
     */
    private void dealOption(@NotNull QuestionVo questionVo, @NotNull Question question) {
        Assert.notEmpty(questionVo.getOptions(), "请添加选项");
        if (ObjectUtil.isNotEmpty(questionVo.getCatalogId())) {
            questionCatalogRepository.findById(questionVo.getCatalogId()).ifPresent(question::setQuestionCatalog);
        }
        Stream<QuestionOption> stream = questionVo.getOptions().stream()
                .filter(option -> ObjectUtil.isNotEmpty(option.getContent()))
                .map(option -> QuestionOption.builder()
                        .content(option.getContent())
                        .result(option.getResult())
                        .build());
        List<QuestionOption> collect = stream.toList();
        questionOptionRepository.saveAllAndFlush(collect);
        question.setOptions(collect);
        questionRepository.saveAndFlush(question);
    }
}
