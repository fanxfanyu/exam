package top.fanxfan.jpa.base.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanxfan.jpa.base.entity.vo.LoginVo;
import top.fanxfan.jpa.base.service.UserService;

/**
 * 登录Controller
 *
 * @author fanxfan
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "登录Controller", description = "登录注册相关控制")
public class LoginController {

    private final UserService userService;

    /**
     * 登录
     *
     * @param longinVo 登录信息
     * @return 响应结果
     */
    @PostMapping
    public ResponseEntity<Boolean> login(@RequestBody LoginVo longinVo) {
        return ResponseEntity.ok(userService.login(longinVo));
    }
}
