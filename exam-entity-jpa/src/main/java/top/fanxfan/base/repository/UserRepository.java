package top.fanxfan.base.repository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import top.fanxfan.base.entity.User;
import top.fanxfan.base.enums.UserTypeEnum;
import top.fanxfan.core.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 用户Repository
 *
 * @author fanxfan
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    Optional<User> findByUserName(@NonNull String userName);

    /**
     * 根据邮箱查找用户
     *
     * @param email 邮箱
     * @return 用户信息
     */
    Optional<User> findByEmail(@NonNull String email);

    /**
     * 根据用户类型查找用户
     *
     * @param userType 用户类型
     * @return 用户信息
     */
    List<User> findByUserType(UserTypeEnum userType);

    /**
     * 查找用户
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    Optional<User> findByMobile(String mobile);
}
