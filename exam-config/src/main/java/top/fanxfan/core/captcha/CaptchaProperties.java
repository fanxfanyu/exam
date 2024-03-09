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
     * 是否开启
     */
    private Boolean enable = false;
    /**
     * 验证码类型
     */
    private CaptchaTypeEnum type = CaptchaTypeEnum.LINE;
    /**
     * 验证码宽度
     */
    private Integer width = 200;
    /**
     * 验证码高度
     */
    private Integer height = 80;
    /**
     * 验证码字符个数
     */
    private Integer length = 6;

    /**
     * 验证码干扰线个数
     */
    private Integer count = 20;

    public void setType(CaptchaTypeEnum captchaType) {
        this.type = captchaType == null ? CaptchaTypeEnum.LINE : captchaType;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable != null && enable;
    }
}
