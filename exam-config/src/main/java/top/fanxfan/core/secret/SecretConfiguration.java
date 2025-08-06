package top.fanxfan.core.secret;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


/**
 * 加密配置
 *
 * @author fanxfan
 */
@AutoConfiguration
@EnableConfigurationProperties(SecretProperties.class)
@Data
@Slf4j
public class SecretConfiguration {

    @Resource
    private SecretProperties secretProperties;

    /**
     * 生成RSA bean对象
     */
    @Bean
    public RSA rsa() {
        return SecureUtil.rsa(secretProperties.getPrivateKey(), secretProperties.getPublicKey());
    }
}
