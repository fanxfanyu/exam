package top.fanxfan.exam.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.base.entity.User;
import top.fanxfan.core.entity.AbstractEntity;
import top.fanxfan.exam.enums.AnswerProcess;

import static top.fanxfan.core.constants.EntityGlobalConstants.USER_EXAM_ENTITY_NAME;
import static top.fanxfan.core.constants.FieldGlobalConstants.EXAM_ID_FIELD;
import static top.fanxfan.core.constants.FieldGlobalConstants.USER_ID;

/**
 * 用户考试
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
@Table(name = USER_EXAM_ENTITY_NAME)
@Schema(description = "用户考试", name = "UserExam")
public class UserExam extends AbstractEntity<UserExam> {
    /**
     * 用户
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = USER_ID, nullable = false)
    private User user;

    /**
     * 考试
     */
    @ManyToOne
    @JoinColumn(name = EXAM_ID_FIELD, nullable = false)
    private Exam exam;

    /**
     * 得分
     */
    private Double score;

    /**
     * 当前状态
     */
    @Enumerated(value = EnumType.STRING)
    private AnswerProcess process;

}
