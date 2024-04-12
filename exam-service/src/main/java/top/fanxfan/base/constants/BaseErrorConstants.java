package top.fanxfan.base.constants;

/**
 * 基础错误常量
 *
 * @author fanxfan
 */
@SuppressWarnings("all")
public interface BaseErrorConstants {
    /**
     * 无效登录方式
     */
    String INVALID_LOGIN_TYPE = "无效登录方式";

    /**
     * 登录失败
     */
    String LOGIN_ERROR = "登录失败";

    /**
     * 密码不正确
     */
    String USERNAME_PASSWORD_NOT_MATCH_MESSAGE = "用户名或密码不正确";

    /**
     * 该账号已被禁用
     */
    String USER_DISABLE = "该账号已被禁用";

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
