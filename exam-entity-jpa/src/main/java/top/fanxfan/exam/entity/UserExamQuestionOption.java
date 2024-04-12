package top.fanxfan.exam.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.core.entity.AbstractEntity;
import top.fanxfan.exam.enums.QuestionResult;
import top.fanxfan.exam.enums.QuestionTypeEnum;

import static top.fanxfan.core.constants.EntityGlobalConstants.QUESTION_OPTION_ENTITY_NAME;
import static top.fanxfan.core.constants.FieldGlobalConstants.USER_EXAM_ID_FIELD;

/**
 * @author fanxfan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = QUESTION_OPTION_ENTITY_NAME)
@Schema(description = "试题选项", name = "QuestionOption")
public class UserExamQuestionOption extends AbstractEntity<UserExamQuestionOption> {

    /**
     * 用户考试
     */
    @ManyToOne
    @JoinColumn(name = USER_EXAM_ID_FIELD, nullable = false)
    private UserExam userExam;

    /**
     * 试题选项类型
     */
    @Schema(description = "试题类型", example = "SINGLE_CHOICE")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QuestionTypeEnum type = QuestionTypeEnum.SINGLE_CHOICE;

    /**
     * 选项内容
     */
    @Schema(description = "选项内容", name = "content")
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 是否为正确选项
     */
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Schema(description = "是否为正确选项", name = "result")
    private QuestionResult result = QuestionResult.WRONG;

    /**
     * 是否选择
     */
    private Integer selected;

    /**
     * 选项分数
     */
    private Integer score;
}
