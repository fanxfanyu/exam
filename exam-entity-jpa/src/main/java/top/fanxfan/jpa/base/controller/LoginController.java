package top.fanxfan.jpa.base.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fanxfan.jpa.base.entity.vo.LoginVo;
import top.fanxfan.jpa.base.service.AuthService;
import top.fanxfan.jpa.base.service.CaptchaService;

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
    @GetMapping("/code")
    public ResponseEntity<Map<String, Object>> code() {
        return ResponseEntity.ok(captchaService.create());
    }

    /**
     * 登录
     *
     * @param longinVo 登录信息
     * @return 响应结果
     */
    @PostMapping
    public ResponseEntity<Boolean> login(@RequestBody LoginVo longinVo) {
        return ResponseEntity.ok(authService.login(longinVo));
    }
}
