package top.fanxfan.core.captcha;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;



/**
 * 验证码配置
 *
 * @author fanxfan
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "top.fanxfan.captcha", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(CaptchaProperties.class)
@Slf4j
public class CaptchaConfiguration {

    @Resource
    private CaptchaProperties captchaProperties;

    /**
     * 创建验证码
     */
    @Bean(name = "captcha")
    public AbstractCaptcha createCaptcha() {
        if (ObjectUtil.isEmpty(captchaProperties)) {
            throw new IllegalArgumentException("验证码配置信息不能为空");
        }
        if (Boolean.FALSE.equals(captchaProperties.getEnable())) {
            return new DefaultCaptcha();
        }
        if (ObjectUtil.isEmpty(captchaProperties.getType())) {
            throw new IllegalArgumentException("验证码配置错误");
        }
        final Integer width = captchaProperties.getWidth();
        final Integer height = captchaProperties.getHeight();
        final Integer length = captchaProperties.getLength();
        final Integer count = captchaProperties.getCount();
        return switch (captchaProperties.getType()) {
            case CIRCLE -> CaptchaUtil.createCircleCaptcha(width, height, length, count);
            case SHEAR -> CaptchaUtil.createShearCaptcha(width, height, length, count);
            case LINE -> CaptchaUtil.createLineCaptcha(width, height, length, count);
            case GIF -> CaptchaUtil.createGifCaptcha(width, height, length);
            default -> throw new IllegalArgumentException("验证码类型不支持");
        };
    }
}
