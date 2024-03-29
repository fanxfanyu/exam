package top.fanxfan.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import top.fanxfan.base.enums.UserTypeEnum;
import top.fanxfan.core.enums.StatusEnum;

/**
 * 用户信息VO
 *
 * @author fanxfan
 */
@Data
@AllArgsConstructor
@Schema(description = "用户信息VO", name = "UserVo")
public class UserVo {

    /**
     * 用户id
     */
    @Schema(description = "用户id", name = "id")
    private Long id;

    /**
     * 用户名
     */
    @Schema(description = "用户名", name = "userName")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 手机号
     */
    @Schema(description = "手机号", name = "mobile")
    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱", name = "email")
    @NotEmpty(message = "邮箱不能为空")
    private String email;

    /**
     * 用户状态
     */
    @Schema(description = "用户状态", name = "userStatus")
    private int userStatus;

    /**
     * 用户类型
     */
    @Schema(description = "用户类型", name = "userType")
    private UserTypeEnum userType;

    /**
     * 展示状态
     */
    @Schema(description = "展示状态", name = "status")
    private StatusEnum status;
}
