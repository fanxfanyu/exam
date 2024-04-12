package top.fanxfan.core.limit.aop;

import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import top.fanxfan.core.limit.LimitTypeEnum;

import java.lang.annotation.*;

/**
 * 限流配置注解
 *
 * @author fanxfan
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisRateLimitConfig {

    /**
     * key值
     */
    String key() default "redis:limit:default:key";

    /**
     * 限流类型
     */
    LimitTypeEnum limitType() default LimitTypeEnum.IP;

    /**
     * 类型
     */
    RateType rateType() default RateType.OVERALL;

    /**
     * 令牌桶容量
     */
    int burstCapacity() default 100;

    /**
     * 过期时间
     */
    int timeout() default 600;

    /**
     * 时间单位
     */
    RateIntervalUnit unit() default RateIntervalUnit.SECONDS;
}
