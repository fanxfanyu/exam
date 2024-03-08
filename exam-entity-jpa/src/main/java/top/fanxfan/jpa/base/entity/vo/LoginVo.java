package top.fanxfan.jpa.base.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import top.fanxfan.jpa.base.enums.LoginTypeEnum;

/**
 * 登录VO
 *
 * @author fanxfan
 */
@Data
@Schema(name = "LoginVo", description = "登录VO")
public class LoginVo {

    /**
     * 账号
     */
    @NotBlank(message = "账号不允许为空")
    @Schema(name = "account", description = "账号")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不允许为空")
    @Schema(name = "password", description = "密码")
    private String password;

    /**
     * 登录方式 {@link LoginTypeEnum#getValue()}
     */
    @NotNull(message = "登录方式不能为空")
    @Schema(name = "type", description = "登录方式")
    private Integer type;

    /**
     * 验证码
     */
    @NotBlank(message = "图片验证码不允许为空")
    @Schema(name = "captcha", description = "图片验证码")
    private String captcha;

    /**
     * 验证码key
     */
    @NotBlank(message = "验证码key不允许为空")
    @Schema(name = "captchaKey", description = "验证码key")
    private String captchaKey;
}
