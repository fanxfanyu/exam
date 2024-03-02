package top.fanxfan.jpa.base.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.core.tools.RedisUtils;
import top.fanxfan.jpa.base.entity.User;
import top.fanxfan.jpa.base.entity.vo.LoginVo;
import top.fanxfan.jpa.base.enums.LoginTypeEnum;
import top.fanxfan.jpa.base.repository.UserRepository;
import top.fanxfan.jpa.base.service.UserService;

import java.time.Duration;

import static top.fanxfan.jpa.base.constants.BaseRedisKeyConstants.SEND_CODE;

/**
 * 用户Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Boolean login(LoginVo longinVo) {
        checkCode(longinVo);
        LoginTypeEnum byValue = LoginTypeEnum.getByValue(longinVo.getType());
        switch (byValue) {
            case VERIFICATION_CODE -> verificationCode(longinVo);
            case EMAIL_LOGIN -> emailLogin(longinVo);
            case USERNAME_LOGIN -> usernameLogin(longinVo);
            default -> throw new RuntimeException("无效登录方式");
        }
        return true;
    }

    /**
     * 校验图形码
     *
     * @param loginVo 登录信息
     */
    private void checkCode(LoginVo loginVo) {
        if (!"1234".equals(loginVo.getCode())) {
            throw new RuntimeException("验证码错误");
        }
    }

    /**
     * 邮箱登录
     *
     * @param loginVo 登录信息
     */
    private void emailLogin(LoginVo loginVo) {
        User user = userRepository.findByEmailAndPassword(loginVo.getAccount(), loginVo.getPassword()).orElseThrow(() -> new RuntimeException("用户不存在"));
        // 是否重新验证邮箱
        StpUtil.login(user.getId());
    }

    /**
     * 用户名登录
     *
     * @param loginVo 登录信息
     */
    private void usernameLogin(LoginVo loginVo) {
        String code = getCode(loginVo.getAccount());
        if (!code.equals(loginVo.getPassword())) {
            throw new RuntimeException("验证码错误");
        }
        User user = userRepository.findByUserNameAndPassword(loginVo.getAccount(), loginVo.getPassword()).orElseThrow(() -> new RuntimeException("用户不存在"));
        StpUtil.login(user.getId());
    }

    /**
     * 验证码登录
     *
     * @param loginVo 登录信息
     */
    private void verificationCode(LoginVo loginVo) {
        // 获取手机验证码,数据库获取、redis获取
        String code = getCode(loginVo.getAccount());
        if (!code.equals(loginVo.getPassword())) {
            throw new RuntimeException("验证码错误");
        }
    }

    /**
     * 获取验证码
     *
     * @param target 目标值
     * @return 验证码
     */
    private String getCode(String target) {
        // 获取手机验证码,数据库获取、redis获取
        String key = formatKey(target);
        String code = Convert.toStr(RedisUtils.getCacheObject(key), "");
        if (ObjectUtil.isEmpty(code)) {
            throw new RuntimeException("验证码已过期");
        }
        return code;
    }

    @Override
    public Boolean sendCode(String target, Integer type) {
        // 随机生成验证码
        String code = RandomUtil.randomString(6);
        // 执行短信发送/执行邮箱发送,并记录日志
        // 记录定时redis中
        String key = formatKey(target);
        // 强制新值
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(10));
        log.error("sendCode {}", code);
        return true;
    }

    /**
     * 处理redis的KEY
     *
     * @param target 目标值
     * @return 结果
     */
    private String formatKey(String target) {
        return String.format(SEND_CODE, target);
    }
}
