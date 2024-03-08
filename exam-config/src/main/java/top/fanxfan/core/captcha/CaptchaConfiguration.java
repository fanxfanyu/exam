package top.fanxfan.core.captcha;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 验证码配置
 *
 * @author fanxfan
 */
@AutoConfiguration
@EnableConfigurationProperties(CaptchaProperties.class)
@Data
@Slf4j
public class CaptchaConfiguration {

    @Resource
    private CaptchaProperties captchaProperties;

    /**
     * 创建验证码
     */
    @Bean(name = "captcha")
    public AbstractCaptcha catchCaptcha() {
        return switch (captchaProperties.getType()) {
            case "circle" ->
                    CaptchaUtil.createCircleCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLength(), 20);
            case "shear" ->
                    CaptchaUtil.createShearCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLength(), 20);
            default ->
                    CaptchaUtil.createLineCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLength(), 20);
        };
    }
}
