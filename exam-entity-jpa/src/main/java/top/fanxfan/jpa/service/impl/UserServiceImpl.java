package top.fanxfan.jpa.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.jpa.repository.UserRepository;
import top.fanxfan.jpa.service.UserService;

/**
 * 用户Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
}
