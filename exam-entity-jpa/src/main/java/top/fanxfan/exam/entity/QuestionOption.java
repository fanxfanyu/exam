package top.fanxfan.exam.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "试题选项", name = "QuestionOption")
public class QuestionOption extends AbstractEntity<QuestionOption> {
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
}

