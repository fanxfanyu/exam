package top.fanxfan.base.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.fanxfan.base.entity.User;
import top.fanxfan.base.search.UserSearchVo;
import top.fanxfan.base.service.UserService;
import top.fanxfan.base.vo.UserVo;

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
     * 新增用户
     *
     * @param userVo 用户信息 {@link UserVo}
     * @return 响应信息
     */
    @Operation(summary = "新增用户")
    @PostMapping
    @SaCheckPermission(value = "user:add", orRole = "super")
    public ResponseEntity<Boolean> add(@RequestBody @Validated UserVo userVo) {
        return ResponseEntity.ok(userService.add(userVo));
    }

    /**
     * 用户列表
     *
     * @param userSearchVo 用户查询信息 {@link UserSearchVo}
     */
    @Operation(summary = "用户列表")
    @GetMapping("/list")
    @SaCheckPermission(value = "user:list", orRole = "super")
    public ResponseEntity<Page<User>> list(@Validated UserSearchVo userSearchVo) {
        return ResponseEntity.ok(userService.list(userSearchVo));
    }

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Operation(summary = "获取用户信息")
    @GetMapping("/{id}")
    @SaCheckPermission(value = "user:info", orRole = "super")
    public ResponseEntity<User> info(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.infoById(id));
    }

    /**
     * 更新用户信息
     *
     * @param userVo 用户信息 {@link UserVo}
     * @return 响应信息
     */
    @Operation(summary = "更新用户信息")
    @PutMapping
    @SaCheckPermission(value = "user:update", orRole = "super")
    public ResponseEntity<Boolean> update(@RequestBody @Validated UserVo userVo) {
        return ResponseEntity.ok(userService.update(userVo));
    }

    /**
     * 重置密码
     *
     * @param userId 用户ID
     * @return 重置结果
     */
    @Operation(summary = "重置密码")
    @PutMapping("/reset/{id}")
    @SaCheckPermission(value = "user:reset", orRole = "super")
    public ResponseEntity<Boolean> reset(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.resetPassword(userId));
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 响应信息
     */
    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    @SaCheckPermission(value = "user:delete", orRole = "super")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    /**
     * 修改用户状态
     *
     * @param id 用户ID
     * @return 响应信息
     */
    @Operation(summary = "修改用户状态")
    @PutMapping("/status/{id}")
    @SaCheckPermission(value = "user:status", orRole = "super")
    public ResponseEntity<Boolean> status(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.status(id));
    }
}
