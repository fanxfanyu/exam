package top.fanxfan.exam.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;
import top.fanxfan.core.entity.AbstractEntity;

import java.util.List;

import static top.fanxfan.core.constants.EntityGlobalConstants.QUESTION_CATALOG_ENTITY_NAME;
import static top.fanxfan.core.constants.EntityGlobalConstants.QUESTION_CATALOG_RELATION_ENTITY_NAME;
import static top.fanxfan.core.constants.FieldGlobalConstants.*;

/**
 * 试题类型
 *
 * @author fanxfan
 */
@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = QUESTION_CATALOG_ENTITY_NAME)
@Schema(description = "试题类型", name = "QuestionCatalog")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public final class QuestionCatalog extends AbstractEntity<QuestionCatalog> {

    /**
     * 名称
     */
    @Schema(description = "分类名称", name = "name")
    private String name;

    /**
     * 排序
     */
    @Schema(description = "排序", name = "sequence")
    private Integer sequence;

    /**
     * 父级分类
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = PARENT_ID_FIELD)
    @ToString.Exclude
    @JsonSerialize
    @Schema(description = "父级分类", name = "parent")
    private QuestionCatalog parent;

    /**
     * 子分类
     */
    @OneToMany(mappedBy = "parent")
    @ToString.Exclude
    @Schema(description = "子分类", name = "children")
    private List<QuestionCatalog> children;

    /**
     * 试题数量
     */
    @Schema(description = "试题数量", name = "questionCount")
    @Transient
    private transient Integer questionCount;

    /**
     * 关联试题
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = QUESTION_CATALOG_RELATION_ENTITY_NAME,
            joinColumns = @JoinColumn(name = QUESTION_CATALOG_ID_FIELD),
            inverseJoinColumns = @JoinColumn(name = QUESTION_ID_FIELD))
    @ToString.Exclude
    @Schema(description = "关联试题", name = "questions")
    private List<Question> questions;
}
