package top.fanxfan.base.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import top.fanxfan.core.entity.AbstractEntity;

import static top.fanxfan.core.constants.EntityGlobalConstants.USER_ENTITY_NAME;

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
@Table(name = USER_ENTITY_NAME, indexes = {
        @Index(name = "userNameIndex", columnList = "userName")
})
@Schema(title = "fanxfan_user", description = "用户信息")
public final class User extends AbstractEntity<User> {

    /**
     * 用户名
     */
    @Schema(name = "userName", description = "用户名")
    @Column(unique = true, nullable = false)
    private String userName;

    /**
     * 密码
     */
    @Schema(hidden = true, description = "密码")
    private String password;

    /**
     * 用户状态
     */
    @Schema(name = "userStatus", description = "用户状态")
    @Column(columnDefinition = "int default 0")
    private int userStatus;

    /**
     * 手机号
     */
    @Schema(name = "mobile", description = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @Schema(name = "email", description = "邮箱")
    private String email;
}
