package top.fanxfan.core.tools;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fanxfan
 */
@Slf4j
public class SecretUtils {

    private static final String PRIVATE_KEY_PROPERTY_NAME = "top.fanxfan.secret.private-key";
    private static final String PUBLIC_KEY_PROPERTY_NAME = "top.fanxfan.secret.public-key";

    private static RSA rsa;

    private SecretUtils() {

    }


    /**
     * 加密
     *
     * @param value 明文
     * @return 密文
     */
    public static String encrypt(final String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        return getEncryptor().encryptBase64(value, KeyType.PrivateKey);
    }

    /**
     * 解密
     *
     * @param value 密文
     * @return 明文
     */
    public static String decrypt(final String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        return getEncryptor().decryptStr(value, KeyType.PublicKey);
    }

    private static RSA getEncryptor() {
        if (rsa != null) {
            return rsa;
        }
        // 构建 rsa
        String privateKey = SpringUtil.getProperty(PRIVATE_KEY_PROPERTY_NAME);
        String publicKey = SpringUtil.getProperty(PUBLIC_KEY_PROPERTY_NAME);
        Assert.notEmpty(privateKey, "配置项({}) 不能为空", PRIVATE_KEY_PROPERTY_NAME);
        Assert.notEmpty(privateKey, "配置项({}) 不能为空", PUBLIC_KEY_PROPERTY_NAME);
        rsa = SecureUtil.rsa(privateKey, publicKey);
        return rsa;
    }
}
