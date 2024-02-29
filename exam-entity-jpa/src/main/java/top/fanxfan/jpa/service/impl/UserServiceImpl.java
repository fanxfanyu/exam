package top.fanxfan.jpa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.fanxfan.jpa.repository.UserRepository;
import top.fanxfan.jpa.service.UserService;

/**
 * 用户Service实现
 *
 * @author fanxfan
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
}
