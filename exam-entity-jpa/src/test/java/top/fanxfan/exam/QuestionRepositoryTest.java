package top.fanxfan.exam;

import cn.hutool.core.util.RandomUtil;
import com.querydsl.core.BooleanBuilder;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import top.fanxfan.SpringBootBaseTest;
import top.fanxfan.core.enums.StatusEnum;
import top.fanxfan.exam.entity.QQuestion;
import top.fanxfan.exam.entity.Question;
import top.fanxfan.exam.entity.QuestionCatalog;
import top.fanxfan.exam.entity.QuestionOption;
import top.fanxfan.exam.enums.QuestionDegreeEnum;
import top.fanxfan.exam.enums.QuestionResult;
import top.fanxfan.exam.enums.QuestionTypeEnum;
import top.fanxfan.exam.repository.QuestionCatalogRepository;
import top.fanxfan.exam.repository.QuestionOptionRepository;
import top.fanxfan.exam.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 试题测试类
 *
 * @author fanxfan
 */
@Slf4j
class QuestionRepositoryTest extends SpringBootBaseTest {
    @Resource
    private QuestionRepository questionRepository;

    @Resource
    private QuestionCatalogRepository questionCatalogRepository;

    @Resource
    private QuestionOptionRepository questionOptionRepository;

    /**
     * 试题新增
     */
    @Test
    void insert() {
        Question build = Question.builder()
                .title("测试试题")
                .build();
        log.error("insert before {}", build);
        questionRepository.saveAndFlush(build);
        log.error("insert after {}", build);
    }

    /**
     * 查询findById
     */
    @Test
    void findById() {
        Question question = questionRepository.findById(1L).orElseThrow(() -> new RuntimeException("element not exist"));
        log.error("result {}", question);
    }

    /**
     * 查询findAll
     */
    @Test
    void find() {
        Page<Question> all = questionRepository.findAll(PageRequest.of(0, 10));
        log.error("find result {}", all);
    }

    /**
     * 查询findAll_Example
     */
    @Test
    void findExample() {
        Question question = new Question();
        question.setId(1L);
        Example<Question> questionExample = Example.of(question);
        Page<Question> all = questionRepository.findAll(questionExample, PageRequest.of(0, 10));
        log.error("all result {}", all);
    }

    /**
     * 查询findAll_Specification
     */
    @Test
    void findSpecification() {
        Page<Question> all = questionRepository.findAll((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), 1L), PageRequest.of(0, 10));
        log.error("all result {}", all);
    }

    /**
     * 查询findAll_Predicate
     */
    @Test
    @Transactional
    void findPredicate() {
        QQuestion question = QQuestion.question;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(question.id.eq(1L));
        Page<Question> all = questionRepository.findAll(booleanBuilder, PageRequest.of(0, 10));
        log.error("all result {}", all);
    }

    /**
     * 删除所有数据
     */
    @Test
    void deleteAll() {
        questionRepository.deleteAll();
        questionCatalogRepository.deleteAll();
        questionOptionRepository.deleteAll();
    }

    /**
     * 新增分类
     */
    @Test
    void insertCatalog() {
        QuestionCatalog java = QuestionCatalog.builder()
                .name("java")
                .sequence(0)
                .status(StatusEnum.SHOW)
                .questionCount(0)
                .build();
        questionCatalogRepository.saveAndFlush(java);
    }

    /**
     * 试题插入测试
     */
    @Test
    void insertQuestion() {
        QuestionCatalog java = questionCatalogRepository.findById(1L).orElseThrow(() -> new RuntimeException("element not exist"));
        List<QuestionOption> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            options.add(QuestionOption.builder()
                    .status(StatusEnum.SHOW)
                    .content(RandomUtil.randomChinese() + "1")
                    .result(i == 0 ? QuestionResult.RIGHT : QuestionResult.WRONG)
                    .build());
        }
        questionOptionRepository.saveAllAndFlush(options);
        Question question = Question.builder()
                .title(RandomUtil.randomChinese() + "")
                .type(QuestionTypeEnum.SINGLE_CHOICE)
                .questionCatalog(java)
                .status(StatusEnum.SHOW)
                .analysis("无解析")
                .answer("A")
                .degree(QuestionDegreeEnum.EASY)
                .options(options)
                .build();
        questionRepository.saveAndFlush(question);
    }

    /**
     * 查询试题
     */
    @Test
    @Transactional
    void showQuestion() {
        Question question = questionRepository.findById(1L).orElseThrow(() -> new RuntimeException("element not exist"));
        log.error("result {}", question);
        log.error("catalog {}", question.getQuestionCatalog());
        log.error("options {}", question.getOptions());
    }

}