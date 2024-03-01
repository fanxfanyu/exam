package top.fanxfan.jpa.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import top.fanxfan.jpa.core.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽象基础实体
 *
 * @author fanxfan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
@EntityListeners(value = AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ToString
public abstract class AbstractEntity<T> implements Serializable, Specification<T> {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 行状态
     */
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(20) default 'SHOW'")
    @Builder.Default
    private StatusEnum status = StatusEnum.SHOW;

    /**
     * 创建时间
     */
    @CreatedDate
    @CreationTimestamp
    @Column(updatable = false)
    @JsonIgnore
    private Date createDate;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @UpdateTimestamp
    @JsonIgnore
    private Date updateDate;

    /**
     * 版本号
     */
    @Version
    @JsonIgnore
    private Integer version;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
