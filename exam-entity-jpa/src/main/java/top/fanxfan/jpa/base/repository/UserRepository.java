package top.fanxfan.jpa.base.repository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import top.fanxfan.jpa.base.entity.User;
import top.fanxfan.jpa.core.repository.BaseRepository;

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


}
