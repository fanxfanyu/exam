package top.fanxfan.jpa.base.constants;

/**
 * 基础redis常量
 *
 * @author fanxfan
 */
@SuppressWarnings("all")
public interface BaseRedisKeyConstants {

    /**
     * 发送验证码前缀
     * KEY格式：send_code:{mobile}
     */
    String SEND_CODE = "send_code:%s";

    /**
     * 验证码KEY
     */
    String CAPTCHA_KEY = "captcha_key:%s";
}
