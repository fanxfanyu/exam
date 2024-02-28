package top.fanxfan.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import top.fanxfan.jpa.core.entity.IdEntity;

/**
 * 用户信息
 *
 * @author fanxfan
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fanxfan_user")
public final class User extends IdEntity {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户状态
     */
    @Column(columnDefinition = "int default 0")
    private int userStatus;

    /**
     * 手机号
     */
    private String mobile;
}
