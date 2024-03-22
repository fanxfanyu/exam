package top.fanxfan.base.service;

import java.util.Map;

/**
 * 验证码Service
 *
 * @author fanxfan
 */
public interface CaptchaService {

    /**
     * 生成验证码并返回图片
     *
     * @return 验证码信息
     */
    Map<String, Object> create();

    /**
     * 校验验证码
     *
     * @param uuid 唯一标识
     * @param code 验证码
     */
    void verify(String uuid, String code);
}
