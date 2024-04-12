package top.fanxfan.exam.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.core.entity.AbstractEntity;
import top.fanxfan.exam.enums.PartModel;

import java.util.List;

import static top.fanxfan.core.constants.EntityGlobalConstants.EXAM_PART_ENTITY_NAME;
import static top.fanxfan.core.constants.FieldGlobalConstants.EXAM_ID_FIELD;

/**
 * 考试组成部分
 *
 * @author fanxfan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Entity
@Table(name = EXAM_PART_ENTITY_NAME)
@Schema(description = "考试组成部分", name = "ExamPart")
public class ExamPart extends AbstractEntity<ExamPart> {

    /**
     * 考试模式
     */
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PartModel model = PartModel.SELECTION_MODE;

    /**
     * 考试
     */
    @ManyToOne
    @JoinColumn(name = EXAM_ID_FIELD, nullable = false)
    private Exam exam;

    /**
     * 试题
     */
    @OneToMany(mappedBy = "examPart", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ExamQuestion> questions;

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
