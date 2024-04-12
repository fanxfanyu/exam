package top.fanxfan.exam.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.core.entity.AbstractEntity;
import top.fanxfan.exam.enums.TimeModel;

import java.util.Date;
import java.util.List;

import static top.fanxfan.core.constants.EntityGlobalConstants.EXAM_ENTITY_NAME;

/**
 * 考试
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
@Table(name = EXAM_ENTITY_NAME)
@Schema(description = "考试", name = "Exam")
public final class Exam extends AbstractEntity<Exam> {

    /**
     * 考试名称
     */
    private String name;

    /**
     * 时间模式
     */
    @Enumerated(EnumType.STRING)
    private TimeModel timeMode;

    /**
     * 限时时长
     */
    private Integer time;

    /**
     * 考试开始时间
     */
    private Date startTime;
    /**
     * 考试结束时间
     */
    private Date endTime;

    /**
     * 考试组成部分
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exam")
    @ToString.Exclude
    private List<ExamPart> parts;

    /**
     * 考试题目
     */
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ExamQuestion> questions;

    /**
     * 总分数
     */
    @Builder.Default
    private Integer score = 0;
}
