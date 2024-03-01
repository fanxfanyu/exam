package top.fanxfan.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.jpa.core.entity.AbstractEntity;

/**
 * 用户信息
 *
 * @author fanxfan
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fanxfan_user", indexes = {
        @Index(name = "userNameIndex", columnList = "userName")
})
public final class User extends AbstractEntity<User> {

    /**
     * 用户名
     */
    @Column(unique = true, nullable = false)
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
