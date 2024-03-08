package top.fanxfan.jpa.base.service;

import top.fanxfan.jpa.base.entity.vo.LoginVo;

/**
 * 身份认证Service
 *
 * @author fanxfan
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param longinVo 登录VO {@link LoginVo}
     * @return 是否登录成功
     */
    Boolean login(LoginVo longinVo);

    /**
     * 发送验证码
     *
     * @param target 目标
     * @param type   发送类型 0 短信验证码 1 邮件验证码
     * @return 是否发送成功
     */
    Boolean sendCode(String target, Integer type);

    /**
     * 校验密码
     *
     * @param password        明文密码
     * @param encryptPassword 密文密码
     * @return 是否匹配
     */
    boolean isPasswordMatch(String password, String encryptPassword);
}
