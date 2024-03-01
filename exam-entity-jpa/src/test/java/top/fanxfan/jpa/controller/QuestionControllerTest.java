package top.fanxfan.jpa.controller;

import com.querydsl.core.BooleanBuilder;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import top.fanxfan.jpa.SpringBootBaseTest;
import top.fanxfan.jpa.entity.exam.QQuestion;
import top.fanxfan.jpa.entity.exam.Question;
import top.fanxfan.jpa.repository.QuestionRepository;

/**
 * 试题测试类
 *
 * @author fanxfan
 */
@Slf4j
class QuestionControllerTest extends SpringBootBaseTest {
    @Resource
    private QuestionRepository questionRepository;

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

}