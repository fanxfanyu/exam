package top.fanxfan.base.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import top.fanxfan.base.enums.UserTypeEnum;
import top.fanxfan.core.enums.StatusEnum;

/**
 * @author fanxfan
 */
@Data
@AllArgsConstructor
public class UserVo {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空")
    private String email;

    /**
     * 用户状态
     */
    private int userStatus;

    /**
     * 用户类型
     */
    private UserTypeEnum userType;

    /**
     * 展示状态
     */
    private StatusEnum status;
}
