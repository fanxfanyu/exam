package top.fanxfan.jpa.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import top.fanxfan.jpa.core.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础信息
 *
 * @author fanxfan
 * @since 基本框架搭建完成
 * @deprecated 官方推荐使用抽象基础类，基本框架搭建完成将移除 {@link AbstractEntity}
 */
@Getter
@Setter
@MappedSuperclass
@RequiredArgsConstructor
@SuperBuilder
@EntityListeners(value = AuditingEntityListener.class)
@Deprecated(since = "基本框架搭建完成", forRemoval = true)
@SuppressWarnings("all")
public class IdEntity implements Serializable {

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
}
