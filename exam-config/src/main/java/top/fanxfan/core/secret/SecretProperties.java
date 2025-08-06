package top.fanxfan.core.secret;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 加密配置
 *
 * @author fanxfan
 */
@Data
@ConfigurationProperties(prefix = "top.fanxfan.secret")
public class SecretProperties {

    /**
     * 私钥
     */
    @NotBlank(message = "私钥不能为空")
    private String privateKey;

    /**
     * 公钥
     */
    @NotBlank(message = "公钥不能为空")
    private String publicKey;

}
