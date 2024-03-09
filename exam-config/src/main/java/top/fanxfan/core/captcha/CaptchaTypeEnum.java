package top.fanxfan.core.captcha;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证码类型枚举
 *
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum CaptchaTypeEnum {

    /**
     * 线条干扰
     */
    LINE(0),

    /**
     * 圆圈干扰
     */
    CIRCLE(1),

    /**
     * 扭曲干扰
     */
    SHEAR(2),

    /**
     * gif
     */
    GIF(3);

    /**
     * 值
     */
    private final Integer value;
}
