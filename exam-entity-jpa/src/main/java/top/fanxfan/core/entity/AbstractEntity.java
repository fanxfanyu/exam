package top.fanxfan.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Schema(name = "abstractEntity", description = "抽象基础实体", implementation = Serializable.class,discriminatorProperty = "type")
@Slf4j
@DynamicUpdate
@DynamicInsert
@SuppressWarnings("all")
public abstract class AbstractEntity<T> implements Serializable {

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
    @Column(columnDefinition = "varchar(20) default 'SHOW'", nullable = false)
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
}
