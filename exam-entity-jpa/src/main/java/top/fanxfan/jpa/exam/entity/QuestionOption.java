package top.fanxfan.jpa.exam.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.jpa.core.entity.AbstractEntity;
import top.fanxfan.jpa.exam.enums.QuestionResult;

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
@Table(name = "fanxfan_question_option")
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
    @ManyToOne(targetEntity = Question.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Question question;
}
