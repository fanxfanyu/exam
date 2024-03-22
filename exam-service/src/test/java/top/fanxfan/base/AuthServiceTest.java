package top.fanxfan.base;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.fanxfan.SpringBootBaseTest;
import top.fanxfan.base.entity.User;
import top.fanxfan.base.enums.LoginTypeEnum;
import top.fanxfan.base.repository.UserRepository;
import top.fanxfan.base.service.AuthService;
import top.fanxfan.base.service.CaptchaService;
import top.fanxfan.base.vo.LoginVo;
import top.fanxfan.core.tools.SecretUtils;

import java.util.List;
import java.util.Map;

/**
 * 登录测试
 *
 * @author fanxfan
 */
@Slf4j
class AuthServiceTest extends SpringBootBaseTest {
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
     * 图形验证码测试
     */
    @Test
    void testCode() {
        Map<String, Object> codeResult = captchaService.create();
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

    /**
     * 测试密码错误
     */
    @Test
    @SneakyThrows
    void testPasswordAttempt() {
        LoginVo loginVo = new LoginVo();
        loginVo.setAccount("fanxfan");
        loginVo.setPassword("000000");
        loginVo.setType(LoginTypeEnum.USERNAME_LOGIN.getValue());
        for (int i = 0; i < 10; i++) {
            try {
                log.error("login count {}", i);
                Boolean login = authService.login(loginVo);
                log.error("login result {}", login);
            } catch (Exception e) {
                log.error("login error {}", e.getMessage());
            }
        }
    }
}