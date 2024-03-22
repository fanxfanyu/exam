package top.fanxfan;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import top.fanxfan.base.service.UserService;

/**
 * 主启动类
 *
 * @author fanxfan
 */
@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
        UserService service = SpringUtil.getBean("userServiceImpl");
        service.initSuper();
        service.initAdmin();
        log.info("初始化完成");
    }

}
