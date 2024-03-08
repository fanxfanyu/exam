package top.fanxfan.jpa.base.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.fanxfan.core.tools.SecretUtils;
import top.fanxfan.jpa.SpringBootBaseTest;
import top.fanxfan.jpa.base.entity.User;
import top.fanxfan.jpa.base.entity.vo.LoginVo;
import top.fanxfan.jpa.base.enums.LoginTypeEnum;
import top.fanxfan.jpa.base.repository.UserRepository;
import top.fanxfan.jpa.base.service.AuthService;
import top.fanxfan.jpa.base.service.CaptchaService;

import java.util.List;
import java.util.Map;

/**
 * 登录测试
 *
 * @author fanxfan
 */
@Slf4j
class LoginControllerTest extends SpringBootBaseTest {
    @Resource
    private UserRepository userRepository;

    @Resource
    private AuthService authService;

    @Resource
    private CaptchaService captchaService;

    /**
     * 初始化测试数据
     */
    @BeforeEach
    void init() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty()) {
            final String password = "123456";
            final String encrypted = SecretUtils.encrypt(password);
            User user = User.builder()
                    .password(encrypted)
                    .mobile("15800000000")
                    .userName("fanxfan")
                    .email("1580000000@qq.com")
                    .userStatus(0)
                    .build();
            userRepository.save(user);
        }
    }

    /**
     * 删除测试数据
     */
    @AfterEach
    void destroy() {
        userRepository.deleteAll();
    }

    /**
     * 图形验证码测试
     */
    @Test
    void testCode() {
        Map<String, String> codeResult = captchaService.create();
        log.error("codeResult {}", codeResult);
    }

    /**
     * 用户登录测试
     */
    @Test
    void testUserLogin() {
        LoginVo loginVo = new LoginVo();
        loginVo.setAccount("fanxfan");
        loginVo.setPassword("123456");
        loginVo.setType(LoginTypeEnum.USERNAME_LOGIN.getValue());
        loginVo.setCaptcha("QUsa");
        loginVo.setCaptchaKey("80a5c15f-accf-4087-999b-1ecd4dd131fa");
        Boolean login = authService.login(loginVo);
        log.error("login result {}", login);
    }
}