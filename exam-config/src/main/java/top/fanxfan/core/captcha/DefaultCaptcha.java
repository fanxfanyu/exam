package top.fanxfan.core.captcha;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import top.fanxfan.core.exception.ServiceException;

import java.awt.*;

/**
 * 默认验证码实现
 *
 * @author fanxfan
 */
public class DefaultCaptcha extends AbstractCaptcha {

    public DefaultCaptcha() {
        super(200, 80, 4, 10);
    }

    public DefaultCaptcha(int width, int height, int codeCount, int interfereCount) {
        super(width, height, codeCount, interfereCount);
    }

    public DefaultCaptcha(int width, int height, CodeGenerator generator, int interfereCount) {
        super(width, height, generator, interfereCount);
    }

    @Override
    public Image createImage(String code) {
        throw new ServiceException("验证码未开启");
    }
}
