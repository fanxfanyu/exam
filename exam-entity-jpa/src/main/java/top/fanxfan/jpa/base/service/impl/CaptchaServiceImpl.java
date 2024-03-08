package top.fanxfan.jpa.base.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.CharSequenceUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.core.exception.ServiceException;
import top.fanxfan.core.tools.RedisUtils;
import top.fanxfan.jpa.base.service.CaptchaService;

import java.time.Duration;
import java.util.Map;

import static top.fanxfan.jpa.base.constants.BaseRedisKeyConstants.CAPTCHA_KEY;

/**
 * @author fanxfan
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource(name = "captcha")
    private final AbstractCaptcha captcha;


    @Override
    public Map<String, String> create() {
        captcha.createCode();
        String uuid = UUID.fastUUID().toString();
        String code = captcha.getCode();
        log.debug("uuid:{}, code:{}", uuid, code);
        RedisUtils.setCacheObject(formatKey(uuid), code, Duration.ofMinutes(3));
        String imageBase64 = captcha.getImageBase64();
        return Map.of("uuid", uuid, "imageBase64", imageBase64);
    }

    @Override
    public void verify(String uuid, String code) {
        String key = formatKey(uuid);
        String cacheValue = Convert.toStr(RedisUtils.getCacheObject(key), "");
        RedisUtils.deleteObject(key);
        if (!CharSequenceUtil.equals(cacheValue, code)) {
            throw new ServiceException("验证码错误");
        }
    }

    /**
     * redis key
     *
     * @param uuid uuid
     * @return key
     */
    private String formatKey(String uuid) {
        return String.format(CAPTCHA_KEY, uuid);
    }
}
