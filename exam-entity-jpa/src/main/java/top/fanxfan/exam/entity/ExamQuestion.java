package top.fanxfan.exam.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.core.entity.AbstractEntity;

import static top.fanxfan.core.constants.EntityGlobalConstants.EXAM_QUESTION_ENTITY_NAME;
import static top.fanxfan.core.constants.FieldGlobalConstants.*;

/**
 * 考试试题
 *
 * @author fanxfan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = EXAM_QUESTION_ENTITY_NAME)
@Schema(description = "试题", name = "Question")
public class ExamQuestion extends AbstractEntity<ExamQuestion> {

    /**
     * 考试
     */
    @ManyToOne
    @JoinColumn(name = EXAM_ID_FIELD, nullable = false)
    private Exam exam;

    /**
     * 考试章节
     */
    @ManyToOne
    @JoinColumn(name = EXAM_PART_ID_FIELD, nullable = false)
    private ExamPart examPart;

    /**
     * 试题
     */
    @ManyToOne
    @JoinColumn(name = QUESTION_ID_FIELD, nullable = false)
    private Question question;

    /**
     * 分数
     */
    @Builder.Default
    private Integer score = 1;

    /**
     * 限时时间，单位秒
     */
    @Builder.Default
    private Integer time = 10;

    /**
     * 顺序号
     */
    @Builder.Default
    private Integer orderNum = 0;

}
