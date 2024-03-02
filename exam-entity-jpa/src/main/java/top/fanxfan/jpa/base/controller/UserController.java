package top.fanxfan.jpa.base.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanxfan.jpa.base.service.UserService;

/**
 * 用户管理Controller
 *
 * @author fanxfan
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "用户管理Controller", description = "用户操作相关")
public class UserController {

    private final UserService userService;
}
