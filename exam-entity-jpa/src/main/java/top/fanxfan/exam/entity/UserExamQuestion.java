package top.fanxfan.exam.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.core.entity.AbstractEntity;
import top.fanxfan.exam.enums.QuestionDegreeEnum;
import top.fanxfan.exam.enums.QuestionTypeEnum;

import java.util.List;

import static top.fanxfan.core.constants.EntityGlobalConstants.USER_EXAM_PART_ENTITY_NAME;
import static top.fanxfan.core.constants.FieldGlobalConstants.*;

/**
 * 用户考试试题
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
@Table(name = USER_EXAM_PART_ENTITY_NAME)
@Schema(description = "用户考试试题", name = "UserExamQuestion")
public class UserExamQuestion extends AbstractEntity<UserExamQuestion> {

    /**
     * 用户考试
     */
    @ManyToOne
    @JoinColumn(name = USER_EXAM_ID_FIELD, nullable = false)
    private UserExam userExam;

    /**
     * 用户考试试题ID
     */
    @ManyToOne
    @JoinColumn(name = USER_EXAM_PART_ID_FIELD, nullable = false)
    private UserExamPart userExamPart;

    /**
     * 用户答案
     */
    @Column(columnDefinition = "TEXT")
    private String userAnswer;

    /**
     * 试题类型
     */
    @Schema(description = "试题类型", example = "SINGLE_CHOICE")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QuestionTypeEnum type = QuestionTypeEnum.SINGLE_CHOICE;

    /**
     * 试题标题
     */
    @Schema(name = "title", description = "试题标题")
    @Column(columnDefinition = "TEXT")
    private String title;

    /**
     * 难易程度
     */
    @Schema(description = "难易程度", example = "EASY", name = "degree")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QuestionDegreeEnum degree = QuestionDegreeEnum.EASY;

    /**
     * 答案解析
     */
    @Schema(name = "analysis", description = "答案解析")
    @Column(columnDefinition = "TEXT")
    private String analysis;

    /**
     * 答案
     * <p>
     * 单选，取选项实体
     * 多选，取选项实体
     * 简答，取当前字段
     * 判断，取当前字段
     * 材料，取当前字段
     * 无效，无答案
     * </p>
     */
    @Schema(name = "answer", description = "答案")
    private String answer;

    /**
     * 试题选项
     */
    @Schema(name = "options", description = "试题选项")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = USER_EXAM_QUESTION_ID_FIELD)
    @ToString.Exclude
    private List<UserExamQuestionOption> options;
}
