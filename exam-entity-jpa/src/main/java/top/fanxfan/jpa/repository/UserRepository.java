package top.fanxfan.jpa.repository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import top.fanxfan.jpa.core.repository.BaseRepository;
import top.fanxfan.jpa.entity.User;

import java.util.Optional;

/**
 * 用户Repository
 *
 * @author fanxfan
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    /**
     * 用户登录验证
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    Optional<User> findByUserNameAndPassword(@NonNull String userName, @NonNull String password);
}
