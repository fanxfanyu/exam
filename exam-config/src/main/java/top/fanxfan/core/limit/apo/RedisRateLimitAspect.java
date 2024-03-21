package top.fanxfan.core.limit.apo;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import top.fanxfan.core.exception.ServiceException;
import top.fanxfan.core.limit.LimitTypeEnum;
import top.fanxfan.core.limit.RedisRateLimiter;
import top.fanxfan.core.tools.RedisUtils;
import top.fanxfan.core.tools.ServletUtils;

import java.lang.reflect.Method;

/**
 * @author fanxfan
 */
@Getter
@Setter
@Slf4j
@Aspect
public class RedisRateLimitAspect {

    private RedisRateLimiter redisRateLimiter;

    private RedisRateLimitAspect() {

    }

    public RedisRateLimitAspect(RedisRateLimiter redisRateLimiter) {
        this.redisRateLimiter = redisRateLimiter;
    }

    @Before("@annotation(redisRateLimitConfig)")
    public void before(JoinPoint joinPoint, RedisRateLimitConfig redisRateLimitConfig) {
        // 获取组合key
        String combineKey = getCombineKey(joinPoint, redisRateLimitConfig);
        // 获取限流配置
        long allowed = redisRateLimiter.isAllowed(
                combineKey,
                redisRateLimitConfig.rateType(),
                redisRateLimitConfig.burstCapacity(),
                redisRateLimitConfig.timeout(),
                redisRateLimitConfig.unit());
        // 判断是否超过访问限制
        if (allowed == -1L) {
            throw new ServiceException("系统繁忙，请稍后再试");
        }
    }

    /**
     * 获取组合key
     *
     * @return 组合key
     */
    private String getCombineKey(JoinPoint joinPoint, RedisRateLimitConfig redisRateLimitConfig) {
        StringBuilder buffer = new StringBuilder(redisRateLimitConfig.key());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取方法名
        String name = method.getName();
        // 获取请求
        HttpServletRequest request = ServletUtils.getRequest();
        // 拼接key
        buffer.append(":").append(name).append(":").append(request.getRequestURI()).append(":");
        // ip限流
        if (redisRateLimitConfig.limitType().equals(LimitTypeEnum.IP)) {
            // 获取request
            String clientIp = JakartaServletUtil.getClientIP(request);
            buffer.append(clientIp);
        } else if (redisRateLimitConfig.limitType().equals(LimitTypeEnum.USER)) {
            String loginId = StpUtil.getLoginIdAsString();
            buffer.append(loginId);
        } else if (redisRateLimitConfig.limitType().equals(LimitTypeEnum.CLUSTER)) {
            // 获取clientId
            String id = RedisUtils.getClient().getId();
            buffer.append(id);
        } else {
            buffer.append("default");
        }
        return buffer.toString();
    }
}
