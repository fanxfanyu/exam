package top.fanxfan.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * jpa配置
 *
 * @author fanxfan
 */
@Configuration
@EnableSpringDataWebSupport
@EnableTransactionManagement
public class JpaConfig {
}
