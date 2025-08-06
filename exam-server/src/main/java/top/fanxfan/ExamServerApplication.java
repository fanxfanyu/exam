package top.fanxfan;

import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.core.spring.EnableDynamicTp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fanxfan
 */
@SpringBootApplication
@EnableDynamicTp
@Slf4j
public class ExamServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamServerApplication.class, args);
    }
}
