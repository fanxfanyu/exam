package top.fanxfan.jpa.base.controller;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import top.fanxfan.jpa.SpringBootBaseTest;
import top.fanxfan.jpa.base.entity.User;
import top.fanxfan.jpa.base.repository.UserRepository;

/**
 * 登录测试
 *
 * @author fanxfan
 */
@Slf4j
class LoginControllerTest extends SpringBootBaseTest {
    @Resource
    private UserRepository userRepository;

    /**
     * 用户登录测试
     */
    @Test
    @SneakyThrows
    void login() {
        User userName = userRepository.findByUserNameAndPassword("userName", "123456").orElseThrow(() -> new RuntimeException("user not exist"));
        log.error("user info {}", userName);
    }
}