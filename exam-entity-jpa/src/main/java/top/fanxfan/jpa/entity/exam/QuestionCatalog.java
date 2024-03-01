package top.fanxfan.jpa.entity.exam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;
import top.fanxfan.jpa.core.entity.AbstractEntity;

import java.util.List;

/**
 * 试题类型
 *
 * @author fanxfan
 */
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = "fanxfan_question_catalog")
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
    @JoinColumn(name = "parentId")
    @ToString.Exclude
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

    @OneToMany(mappedBy = "questionCatalog")
    @ToString.Exclude
    private List<Question> questions;
}
