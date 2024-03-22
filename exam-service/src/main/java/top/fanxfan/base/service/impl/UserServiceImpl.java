package top.fanxfan.base.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import top.fanxfan.base.service.UserService;
import top.fanxfan.base.entity.User;
import top.fanxfan.base.repository.UserRepository;

/**
 * 用户Service实现类
 *
 * @author fanxfan
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> list(PageRequest page) {
        return userRepository.findAll(page);
    }
}
