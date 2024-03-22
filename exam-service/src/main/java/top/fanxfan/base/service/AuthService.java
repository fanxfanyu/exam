package top.fanxfan.base.service;

import top.fanxfan.base.vo.ChangePasswordVo;
import top.fanxfan.base.vo.LoginVo;

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
     * @param account 账号
     * @param type    发送类型 0 短信验证码 1 邮件验证码
     * @return 是否发送成功
     */
    Boolean sendCode(String account, Integer type);

    /**
     * 修改密码
     *
     * @param changePasswordVo {@link ChangePasswordVo}
     * @return 是否修改成功
     */
    Boolean changePassword(ChangePasswordVo changePasswordVo);
}
