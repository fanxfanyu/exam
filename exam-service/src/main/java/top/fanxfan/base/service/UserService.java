package top.fanxfan.base.service;

import org.springframework.data.domain.Page;
import top.fanxfan.base.entity.User;
import top.fanxfan.base.search.UserSearchVo;
import top.fanxfan.base.vo.UserVo;

/**
 * 用户Service
 *
 * @author fanxfan
 */
public interface UserService {

    /**
     * 分页查询
     *
     * @param userSearchVo 查询条件 {@link UserSearchVo}
     * @return 用户列表
     */
    Page<User> list(UserSearchVo userSearchVo);


    /**
     * 添加用户
     *
     * @param userVo 用户信息 {@link UserVo}
     * @return 是否添加成功
     */
    Boolean add(UserVo userVo);

    /**
     * 初始化管理员账号
     */
    void initSuper();

    /**
     * 初始化管理员账号
     */
    void initAdmin();

    /**
     * @param id 用户id
     * @return 用户信息 {@link User}
     */
    User infoById(Long id);

    /**
     * 更新用户信息
     *
     * @param userVo 用户信息 {@link UserVo}
     * @return 是否更新成功
     */
    Boolean update(UserVo userVo);

    /**
     * 重置密码
     *
     * @param userId 用户id
     * @return 是否更新成功
     */
    Boolean resetPassword(Long userId);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    Boolean deleteById(Long id);

    /**
     * 禁用用户
     *
     * @param id 用户id
     * @return 是否禁用成功
     */
    Boolean status(Long id);
}
