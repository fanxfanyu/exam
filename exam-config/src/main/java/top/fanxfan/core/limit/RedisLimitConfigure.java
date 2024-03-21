package top.fanxfan.core.limit;

import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.fanxfan.core.limit.apo.RedisRateLimitAspect;

/**
 * 限流配置
 *
 * @author fanxfan
 */
@Configuration
public class RedisLimitConfigure {

    /**
     * 限流redis配置
     */
    @Bean(name = "limitRedisTemplate")
    public RedisTemplate<String, Object> createLimitRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
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
        //
        redisLimitTemplate.afterPropertiesSet();
        return redisLimitTemplate;
    }

    @Bean
    public RedisRateLimiter redisRateLimiter(RedissonClient redissonClient) {
        return new RedisRateLimiter(redissonClient);
    }

    @Bean
    public RedisRateLimitAspect redisRateLimitAspect(RedisRateLimiter redisRateLimiter) {
        return new RedisRateLimitAspect(redisRateLimiter);
    }
}
