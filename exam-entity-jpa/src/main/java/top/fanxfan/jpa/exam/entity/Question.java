package top.fanxfan.jpa.exam.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.jpa.core.entity.AbstractEntity;
import top.fanxfan.jpa.exam.enums.QuestionDegreeEnum;
import top.fanxfan.jpa.exam.enums.QuestionTypeEnum;

import java.util.List;

/**
 * 试题
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
@Table(name = "fanxfan_question", indexes = {
        @Index(name = "typeIndex", columnList = "type"),
        @Index(name = "degreeIndex", columnList = "degree")
})
public class Question extends AbstractEntity<Question> {

    /**
     * 试题类型
     */
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QuestionTypeEnum type = QuestionTypeEnum.SINGLE_CHOICE;
    /**
     * 试题标题
     * <p>
     * <h3>lob的优缺点</h3>
     *     <ul>
     *         优点
     *         <li>空间效率: @Lob可以将大对象（如Blob、Clob等）存储在数据库的二进制大对象（BLOB）或字符大对象（CLOB）列中，这样可以节省表的空间，因为这种类型的列通常比普通列能存储更多的数据。</li>
     *         <li>性能优化: @Lob可以优化数据的检索和更新，因为它允许数据库直接从磁盘读取或写入大对象，而不必像处理普通列那样逐个字节地读取或写入。</li>
     *         <li>灵活性: @Lob可以应用于任何类型的持久化属性或字段，这意味着你可以将任何类型的数据（不仅仅是Blob和Clob）存储在大对象中。</li>
     *     </ul>
     *     <ul>
     *         缺点
     *         <li>复杂性: @Lob的使用可能会增加系统的复杂性，因为它需要数据库的特殊支持，并且可能需要特殊的查询语句来检索或更新数据。</li>
     *         <li>性能开销: 虽然@Lob可以提高数据处理的效率，但它也可能带来一些性能开销，例如，当检索或更新大对象时，可能需要花费更多的时间和资源。</li>
     *         <li>数据一致性问题: 由于大对象不能像普通列一样简单地复制或移动，所以它们可能会引起一些数据一致性的问题，例如，如果一个对象被修改但未完全保存，那么在系统故障恢复期间可能会出现不一致的情况。</li>
     *     </ul>
     * </p>
     */
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "TEXT")
    private String title;

    /**
     * 难易程度
     */
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QuestionDegreeEnum degree = QuestionDegreeEnum.EASY;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "catalogId")
    private QuestionCatalog questionCatalog;

    /**
     * 答案解析
     */
    @Column(columnDefinition = "TEXT")
    private String analysis;

    /**
     * 答案
     * <p>
     * 单选，取选项实体
     * 多选，取选项实体
     * 简答，取当前字段
     * 判断，取当前字段
     * 材料，取当前字段
     * 无效，无答案
     * </p>
     */
    private String answer;

    /**
     * 材料题问题
     */
    @OneToMany
    @JoinColumn(name = "parentId")
    @ToString.Exclude
    private List<Question> children;

    /**
     * 试题选项
     */
    @OneToMany
    @JoinColumn(name = "questionId")
    @ToString.Exclude
    private List<QuestionOption> options;
}
