package top.fanxfan.base.enums;

import cn.hutool.core.util.ArrayUtil;
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
    USERNAME_LOGIN(0, "USERNAME_LOGIN", "用户名登录"),

    /**
     * 邮箱登录
     */
    EMAIL_LOGIN(1, "EMAIL_LOGIN", "邮箱登录"),

    /**
     * 验证码登录
     */
    VERIFICATION_CODE(2, "VERIFICATION_CODE", "验证码登录");

    /**
     * 值
     */
    private final Integer value;

    /**
     * 编码
     */
    private final String code;

    /**
     * 名称
     */
    private final String name;

    /**
     * 获取登录方式
     *
     * @param value 值
     * @return 登录方式
     */
    public static LoginTypeEnum getByValue(Integer value) {
        return ArrayUtil.firstMatch((obj) -> value.equals(obj.getValue()), values());
    }
}
