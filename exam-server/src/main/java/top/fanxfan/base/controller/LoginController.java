package top.fanxfan.base.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.fanxfan.base.service.AuthService;
import top.fanxfan.base.service.CaptchaService;
import top.fanxfan.base.vo.ChangePasswordVo;
import top.fanxfan.base.vo.LoginVo;
import top.fanxfan.core.limit.LimitTypeEnum;
import top.fanxfan.core.limit.aop.RedisRateLimitConfig;

import java.util.Map;

/**
 * 登录Controller
 *
 * @author fanxfan
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "登录Controller", description = "登录注册相关控制")
public class LoginController {

    private final AuthService authService;

    private final CaptchaService captchaService;

    /**
     * 获取验证码
     *
     * @return 响应结果
     */
    @Operation(summary = "获取验证码")
    @GetMapping("/code")
    @SaIgnore
    @RedisRateLimitConfig(key = "login:code:key", rateType = RateType.PER_CLIENT, burstCapacity = 5)
    public ResponseEntity<Map<String, Object>> code() {
        return ResponseEntity.ok(captchaService.create());
    }

    /**
     * 登录
     *
     * @param longinVo 登录信息 {@link LoginVo}
     * @return 响应结果
     */
    @Operation(summary = "登录")
    @PostMapping
    @SaIgnore
    @RedisRateLimitConfig(key = "login:key", burstCapacity = 10)
    public ResponseEntity<Boolean> login(@RequestBody @Validated LoginVo longinVo) {
        return ResponseEntity.ok(authService.login(longinVo));
    }

    /**
     * 退出登录
     */
    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout() {
        StpUtil.logout();
        return ResponseEntity.ok(true);
    }

    /**
     * 修改密码
     *
     * @param changePasswordVo 修改密码VO {@link ChangePasswordVo}
     * @return 响应结果
     */
    @Operation(summary = "修改密码")
    @SaCheckLogin
    @PutMapping("/change")
    @RedisRateLimitConfig(key = "login:change:password", burstCapacity = 5, limitType = LimitTypeEnum.USER, timeout = 10, unit = RateIntervalUnit.MINUTES)
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordVo changePasswordVo) {
        return ResponseEntity.ok(authService.changePassword(changePasswordVo));
    }

    /**
     * 发送短信验证码
     *
     * @param account 手机号
     * @param type    短信类型
     * @return 响应结果
     */
    @Operation(summary = "发送短信验证码")
    @SaIgnore
    @PostMapping("/{account}/{type}")
    @RedisRateLimitConfig(key = "login:send:code", burstCapacity = 5)
    public ResponseEntity<Boolean> sendSmsCode(@PathVariable String account, @PathVariable Integer type) {
        return ResponseEntity.ok(authService.sendCode(account, type));
    }
}
