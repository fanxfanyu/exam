package top.fanxfan.jpa.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import top.fanxfan.jpa.base.entity.User;

/**
 * 用户Service
 *
 * @author fanxfan
 */
public interface UserService {

    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return 用户列表
     */
    Page<User> list(PageRequest page);
}
