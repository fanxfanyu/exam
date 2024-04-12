package top.fanxfan.core.limit;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * 限流器
 *
 * @author fanxfan
 */
@Component
@RequiredArgsConstructor
public class RedisRateLimiter {

    private final RedissonClient redissonClient;

    /**
     * 限流器限制器
     *
     * @return 返回true表示允许访问，false表示拒绝访问
     */
    public long isAllowed(String key, RateType type, int permits, long timeout, RateIntervalUnit unit) {
        // 获取限流器
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        // 设置每秒新增的permits数量
        rateLimiter.trySetRate(type, permits, timeout, unit);
        // 尝试获取permits
        if (rateLimiter.tryAcquire()) {
            return rateLimiter.availablePermits();
        }
        return -1L;
    }
}
