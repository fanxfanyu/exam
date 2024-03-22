package top.fanxfan.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
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
import top.fanxfan.core.enums.StatusEnum;

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
@Schema(name = "abstractEntity", description = "抽象基础实体", implementation = Specification.class)
public abstract class AbstractEntity<T> implements Serializable, Specification<T> {
    /**
     * 主键id
     */
    @Schema(name = "id", description = "主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 行状态
     */
    @Schema(name = "status", description = "行状态", oneOf = StatusEnum.class)
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
    @Schema(name = "createDate", hidden = true, description = "创建时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @UpdateTimestamp
    @JsonIgnore
    @Schema(name = "updateDate", hidden = true, description = "更新时间")
    private Date updateDate;

    /**
     * 版本号
     */
    @Version
    @JsonIgnore
    @Schema(name = "version", hidden = true, description = "版本号")
    private Integer version;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
