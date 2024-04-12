package top.fanxfan.core.limit;

import lombok.Data;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 默认限流控制
 *
 * @author fanxfan
 */
@Data
@ConfigurationProperties(prefix = "redis.limit")
public class RedisLimitProperties {

    /**
     * 默认的key
     */
    private String key = "default";

    /**
     * 默认的限流类型
     */
    private LimitTypeEnum limitType = LimitTypeEnum.IP;

    /**
     * 客户端类型
     */
    private RateType rateType = RateType.OVERALL;

    /**
     * 桶大小
     */
    private int burstCapacity = 50;

    /**
     * 过期时间
     */
    private int timeout = 600;

    /**
     * 限流时间单位
     */
    private RateIntervalUnit rateInterval = RateIntervalUnit.SECONDS;

}
