package top.fanxfan.exam.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public final class QuestionCatalog extends AbstractEntity<QuestionCatalog> {

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 父级分类
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = PARENT_ID_FIELD)
    @ToString.Exclude
    @JsonSerialize
    private QuestionCatalog parent;

    /**
     * 子分类
     */
    @OneToMany(mappedBy = "parent")
    @ToString.Exclude
    private List<QuestionCatalog> children;

    /**
     * 试题数量
     */
    private transient Integer questionCount;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = QUESTION_CATALOG_RELATION_ENTITY_NAME,
            joinColumns = @JoinColumn(name = QUESTION_CATALOG_ID_FIELD),
            inverseJoinColumns = @JoinColumn(name = QUESTION_ID_FIELD))
    @ToString.Exclude
    private List<Question> questions;
}
