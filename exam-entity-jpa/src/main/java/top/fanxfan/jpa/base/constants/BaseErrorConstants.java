package top.fanxfan.jpa.base.constants;

/**
 * 基础错误常量
 *
 * @author fanxfan
 */
@SuppressWarnings("all")
public interface BaseErrorConstants {

    /**
     * 密码不正确
     */
    String PASSWORD_NOT_MATCH_MESSAGE = "密码不正确";

    /**
     * 用户不存在
     */
    String USER_NOT_EXIST_MESSAGE = "用户不存在";

    /**
     * 验证码已过期
     */
    String VERIIFY_CODE_INVIDE = "验证码已过期";

    /**
     * 验证码错误
     */
    String VERIIFY_CODE_ERROR = "验证码错误";
}
