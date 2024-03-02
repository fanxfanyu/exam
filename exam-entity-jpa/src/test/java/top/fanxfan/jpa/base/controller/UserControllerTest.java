package top.fanxfan.jpa.base.controller;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import top.fanxfan.jpa.SpringBootBaseTest;
import top.fanxfan.jpa.base.entity.User;
import top.fanxfan.jpa.base.repository.UserRepository;

/**
 * 用户测试类
 *
 * @author fanxfan
 */
@Slf4j
class UserControllerTest extends SpringBootBaseTest {
    @Resource
    private UserRepository userRepository;

    /**
     * 插入新增测试
     */
    @Test
    @SneakyThrows
    void insert() {
        User userName = User.builder()
                .userName("userName")
                .mobile("15800000000")
                .password("123456")
                .build();
        log.error("save before {}", userName);
        User save = userRepository.saveAndFlush(userName);
        log.error("save after {}", save);
    }


}