package top.fanxfan;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

/**
 * 公共基础测试类
 *
 * @author fanxfan
 */
@SpringBootTest(classes = SpringBootBaseTest.class, properties = {"spring.profiles.active=test"})
@EnableAutoConfiguration
@ComponentScan(basePackages = "top.fanxfan")
public abstract class SpringBootBaseTest {
}
