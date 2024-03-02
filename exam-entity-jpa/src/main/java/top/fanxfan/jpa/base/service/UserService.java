package top.fanxfan.jpa.base.service;

import top.fanxfan.jpa.base.entity.vo.LoginVo;

/**
 * 用户Service
 *
 * @author fanxfan
 */
public interface UserService {

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
}
