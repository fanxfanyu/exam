package top.fanxfan.core.limit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 限流配置
 *
 * @author fanxfan
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties({RedisLimitProperties.class})
public class RedisLimitConfigure {

    /**
     * 限流redis配置
     */
    @Bean(name = "limitRedisTemplate")
    public RedisTemplate<String, Object> createLimitRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("创建限流redisTemplate");
        RedisTemplate<String, Object> redisLimitTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisLimitTemplate.setConnectionFactory(redisConnectionFactory);
        // 配置序列化方式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // key序列化
        redisLimitTemplate.setKeySerializer(new StringRedisSerializer());
        // value序列化
        redisLimitTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash key序列化
        redisLimitTemplate.setHashKeySerializer(new StringRedisSerializer());
        // hash value序列化
        redisLimitTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 重新设置数据
        redisLimitTemplate.afterPropertiesSet();
        return redisLimitTemplate;
    }
}
