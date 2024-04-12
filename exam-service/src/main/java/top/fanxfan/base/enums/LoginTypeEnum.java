package top.fanxfan.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录方式
 *
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    /**
     * 用户名登录
     */
    USERNAME_LOGIN("USERNAME_LOGIN", "用户名登录"),

    /**
     * 邮箱登录
     */
    EMAIL_LOGIN("EMAIL_LOGIN", "邮箱登录"),

    /**
     * 验证码登录
     */
    VERIFICATION_CODE("VERIFICATION_CODE", "验证码登录");

    /**
     * 编码
     */
    private final String code;

    /**
     * 名称
     */
    private final String name;
}
