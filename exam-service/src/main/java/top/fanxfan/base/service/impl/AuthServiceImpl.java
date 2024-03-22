package top.fanxfan.base.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.base.entity.User;
import top.fanxfan.base.enums.LoginTypeEnum;
import top.fanxfan.base.repository.UserRepository;
import top.fanxfan.base.service.AuthService;
import top.fanxfan.base.service.CaptchaService;
import top.fanxfan.base.vo.LoginVo;
import top.fanxfan.core.exception.ServiceException;
import top.fanxfan.core.tools.RedisUtils;
import top.fanxfan.core.tools.SecretUtils;

import java.time.Duration;

import static top.fanxfan.base.constants.BaseErrorConstants.*;
import static top.fanxfan.core.constants.BaseRedisKeyConstants.PASSWORD_ATTEMPT_COUNT;
import static top.fanxfan.core.constants.BaseRedisKeyConstants.SEND_CODE;

/**
 * 身份认证Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final CaptchaService captchaService;

    private static final Integer MAX_ATTEMPTS = 5;

    @Override
    public Boolean login(final LoginVo loginVo) {
        // 验证登录信息
        Assert.notNull(loginVo, "登录信息不能为空");
        // 验证验证码
        captchaService.verify(loginVo.getCaptchaKey(), loginVo.getCaptcha());
        // 获取登录类型
        LoginTypeEnum byValue = LoginTypeEnum.getByValue(loginVo.getType());
        switch (byValue) {
            case VERIFICATION_CODE -> verificationCode(loginVo);
            case EMAIL_LOGIN -> emailLogin(loginVo);
            case USERNAME_LOGIN -> usernameLogin(loginVo);
            default -> throw new ServiceException("无效登录方式");
        }
        return true;
    }

    /**
     * 邮箱登录
     *
     * @param loginVo 登录信息
     */
    private void emailLogin(LoginVo loginVo) {
        User user = userRepository.findByEmail(loginVo.getAccount()).orElseThrow(() -> new ServiceException(USER_NOT_EXIST_MESSAGE));
        if (!isPasswordMatch(user.getId(), loginVo.getPassword(), user.getPassword())) {
            throw new ServiceException(PASSWORD_NOT_MATCH_MESSAGE);
        }
        // 是否重新验证邮箱
        StpUtil.login(user.getId());
    }

    /**
     * 用户名登录
     *
     * @param loginVo 登录信息
     */
    private void usernameLogin(LoginVo loginVo) {
        User user = userRepository.findByUserName(loginVo.getAccount()).orElseThrow(() -> new ServiceException(USER_NOT_EXIST_MESSAGE));
        if (!isPasswordMatch(user.getId(), loginVo.getPassword(), user.getPassword())) {
            throw new ServiceException(PASSWORD_NOT_MATCH_MESSAGE);
        }
        StpUtil.login(user.getId());
    }

    /**
     * 验证码登录
     *
     * @param loginVo 登录信息
     */
    private void verificationCode(LoginVo loginVo) {
        // 获取手机验证码,数据库获取 or redis获取
        String code = getCode(loginVo.getAccount());
        if (!code.equals(loginVo.getPassword())) {
            throw new ServiceException(PASSWORD_NOT_MATCH_MESSAGE);
        }
    }

    /**
     * 获取验证码
     *
     * @param account 账号
     * @return 验证码
     */
    private String getCode(String account) {
        // 获取手机验证码,数据库获取、redis获取
        String key = formatKey(account);
        String code = Convert.toStr(RedisUtils.getCacheObject(key), "");
        if (ObjectUtil.isEmpty(code)) {
            throw new ServiceException(VERIIFY_CODE_INVIDE);
        }
        return code;
    }

    @Override
    public Boolean sendCode(String account, Integer type) {
        // 随机生成验证码
        String code = RandomUtil.randomString(6);
        // 执行短信发送/执行邮箱发送,并记录日志
        // 记录定时redis中
        String key = formatKey(account);
        // 强制新值
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(10));
        log.error("sendCode {}", code);
        return true;
    }

    /**
     * 处理redis的KEY
     *
     * @param account 账号
     * @return 结果
     */
    private String formatKey(String account) {
        return String.format(SEND_CODE, account);
    }


    /**
     * 处理redis的KEY
     *
     * @param userId 用户id
     * @return 结果
     */
    private String formatPasswordAttemptKey(Long userId) {
        return String.format(PASSWORD_ATTEMPT_COUNT, userId);
    }

    @Override
    public boolean isPasswordMatch(Long userId, @NonNull String password, @NonNull String encryptPassword) {
        String key = formatPasswordAttemptKey(userId);
        long atomicValue = RedisUtils.getAtomicValue(key);
        if (atomicValue > MAX_ATTEMPTS) {
            long timeToLive = RedisUtils.getTimeToLive(key);
            if (ObjectUtil.isEmpty(timeToLive) || timeToLive <= 0) {
                RedisUtils.expire(key, Duration.ofMinutes(10));
            }
            throw new ServiceException("密码错误次数过多,请10分钟后重试");
        }
        boolean equals = CharSequenceUtil.equals(SecretUtils.encrypt(password), encryptPassword);
        if (!equals) {
            RedisUtils.incrAtomicValue(key);
        }
        return equals;
    }
}
