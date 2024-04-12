package top.fanxfan.core.limit.aop;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RateIntervalUnit;
import org.springframework.stereotype.Component;
import top.fanxfan.core.exception.ServiceException;
import top.fanxfan.core.limit.LimitTypeEnum;
import top.fanxfan.core.limit.RedisLimitProperties;
import top.fanxfan.core.limit.RedisRateLimiter;
import top.fanxfan.core.tools.RedisUtils;
import top.fanxfan.core.tools.ServletUtils;

import java.lang.reflect.Method;

/**
 * 限流切面
 *
 * @author fanxfan
 */
@Getter
@Setter
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedisRateLimitAspect {

    private final RedisRateLimiter redisRateLimiter;
    private final RedisLimitProperties redisLimitProperties;


    //    @Before("@annotation(redisRateLimitConfig)")
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
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取方法名
        String name = method.getName();
        return getCombineKey(name, redisRateLimitConfig.key(), redisRateLimitConfig.limitType());
    }

    /**
     * 获取组合key
     *
     * @param name      方法名
     * @param key       自定义键值
     * @param limitType 限流类型
     * @return 组合key
     */
    private String getCombineKey(String name, String key, LimitTypeEnum limitType) {
        StringBuilder buffer = new StringBuilder(key);
        // 获取请求
        HttpServletRequest request = ServletUtils.getRequest();
        // 拼接key
        buffer.append(":").append(name).append(":").append(request.getRequestURI()).append(":");
        // ip限流
        if (limitType.equals(LimitTypeEnum.IP)) {
            // 获取request
            String clientIp = JakartaServletUtil.getClientIP(request);
            buffer.append(clientIp);
        } else if (limitType.equals(LimitTypeEnum.USER)) {
            String loginId = StpUtil.getLoginIdAsString();
            buffer.append(loginId);
        } else if (limitType.equals(LimitTypeEnum.CLUSTER)) {
            // 获取clientId
            String id = RedisUtils.getClient().getId();
            buffer.append(id);
        } else {
            buffer.append("default");
        }
        return buffer.toString();
    }


    @Before(value = "execution(* top.fanxfan.*.controller.*.*(..)))")
    public void before(JoinPoint point) {
        log.error("before execution");
        // 获取方法对象
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获取注解RedisRateLimitConfig
        Method method = signature.getMethod();
        String name = method.getName();
        RedisRateLimitConfig redisRateLimitConfig;
        long allowed;
        if ((redisRateLimitConfig = AnnotationUtil.getAnnotation(method, RedisRateLimitConfig.class)) != null) {
            // 获取组合key
            String combineKey = getCombineKey(name, redisRateLimitConfig.key(), redisRateLimitConfig.limitType());
            // 获取限流配置
            allowed = redisRateLimiter.isAllowed(
                    combineKey,
                    redisRateLimitConfig.rateType(),
                    redisRateLimitConfig.burstCapacity(),
                    redisRateLimitConfig.timeout(),
                    redisRateLimitConfig.unit());
        } else {
            // 获取组合key
            String combineKey = getCombineKey(name, redisLimitProperties.getKey(), LimitTypeEnum.IP);
            // 获取限流配置
            allowed = redisRateLimiter.isAllowed(
                    combineKey,
                    redisLimitProperties.getRateType(),
                    redisLimitProperties.getBurstCapacity(),
                    redisLimitProperties.getTimeout(),
                    RateIntervalUnit.SECONDS);
        }
        // 判断是否超过访问限制
        if (allowed == -1L) {
            throw new ServiceException("系统繁忙，请稍后再试");
        }
    }
}
