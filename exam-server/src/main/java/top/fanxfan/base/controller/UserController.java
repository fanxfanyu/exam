package top.fanxfan.base.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanxfan.base.service.UserService;
import top.fanxfan.base.entity.User;

/**
 * 用户管理Controller
 *
 * @author fanxfan
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@SaCheckLogin
@Tag(name = "用户管理Controller", description = "用户操作相关")
public class UserController {

    private final UserService userService;

    /**
     * 获取用户列表
     *
     * @param pageNumber 分页页码
     * @param pageSize   分页数量
     * @return 用户列表
     */
    @GetMapping("/list")
    @SaCheckPermission("user:list")
    @Tag(name = "获取用户列表", description = "获取用户列表")
    public ResponseEntity<Page<User>> list(Integer pageNumber, Integer pageSize) {
        return ResponseEntity.ok(userService.list(PageRequest.of(pageNumber, pageSize)));
    }

}
