package top.fanxfan.jpa.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.jpa.core.repository.BaseRepository;
import top.fanxfan.jpa.entity.User;

/**
 * 用户Repository
 *
 * @author fanxfan
 */
@Repository
public interface UserRepository extends BaseRepository<User> {
}
