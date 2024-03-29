package top.fanxfan.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 修改密码
 *
 * @author fanxfan
 */
@Data
@AllArgsConstructor
@Schema(name = "ChangePasswordVo", description = "修改密码")
public class ChangePasswordVo {

    /**
     * 用户id
     */
    @Schema(description = "用户id", name = "userId")
    private Long userId;

    /**
     * 旧密码
     */
    @Schema(description = "旧密码", name = "newPassword")
    private String oldPassword;

    /**
     * 新密码
     */
    @Schema(description = "新密码", name = "newPassword")
    private String newPassword;
}
