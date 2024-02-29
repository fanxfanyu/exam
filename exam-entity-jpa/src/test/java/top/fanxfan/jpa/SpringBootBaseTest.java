package top.fanxfan.jpa;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 公共基础测试类
 *
 * @author fanxfan
 */
@SpringBootTest(classes = SpringBootBaseTest.class)
@EnableAutoConfiguration
public abstract class SpringBootBaseTest {
}
