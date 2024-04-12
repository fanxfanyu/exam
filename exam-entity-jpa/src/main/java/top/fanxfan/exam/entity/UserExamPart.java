package top.fanxfan.exam.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.core.entity.AbstractEntity;

import java.util.List;

import static top.fanxfan.core.constants.EntityGlobalConstants.USER_EXAM_PART_ENTITY_NAME;
import static top.fanxfan.core.constants.FieldGlobalConstants.USER_EXAM_ID_FIELD;

/**
 * 用户考试组成部分
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
@Schema(description = "用户考试组成部分", name = "UserExamPart")
public class UserExamPart extends AbstractEntity<UserExamPart> {

    /**
     * 考试
     */
    @ManyToOne
    @JoinColumn(name = USER_EXAM_ID_FIELD, nullable = false)
    private UserExam userExam;

    /**
     * 试题
     */
    @OneToMany(mappedBy = "userExamPart", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<UserExamQuestion> questions;

    /**
     * 名称
     */
    private String name;

    /**
     * 顺序
     */
    @Builder.Default
    private Integer orderNum = 0;
}
