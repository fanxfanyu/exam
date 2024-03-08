package top.fanxfan.core.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 验证码配置
 *
 * @author fanxfan
 */
@Data
@ConfigurationProperties(prefix = "top.fanxfan.captcha")
public class CaptchaProperties {
    /**
     * 验证码类型
     */
    private String type;
    /**
     * 验证码宽度
     */
    private Integer width;
    /**
     * 验证码高度
     */
    private Integer height;
    /**
     * 验证码字符个数
     */
    private Integer length;
}
