package top.fanxfan.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * jpa配置
 *
 * @author fanxfan
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableTransactionManagement
public class JpaConfig {
}
