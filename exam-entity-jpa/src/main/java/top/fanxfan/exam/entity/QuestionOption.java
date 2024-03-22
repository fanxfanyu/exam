package top.fanxfan.exam.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.core.entity.AbstractEntity;
import top.fanxfan.exam.enums.QuestionResult;

import static top.fanxfan.core.constants.EntityGlobalConstants.QUESTION_OPTION_ENTITY_NAME;

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
public class QuestionOption extends AbstractEntity<QuestionOption> {
    /**
     * 选项内容
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 是否为正确选项
     */
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QuestionResult result = QuestionResult.WRONG;

    /**
     * 试题
     */
    @ManyToOne
    @ToString.Exclude
    private Question question;
}
