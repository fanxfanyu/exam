package top.fanxfan.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import top.fanxfan.base.entity.QUser;
import top.fanxfan.base.entity.User;
import top.fanxfan.base.enums.UserTypeEnum;
import top.fanxfan.base.repository.UserRepository;
import top.fanxfan.base.search.UserSearchVo;
import top.fanxfan.base.service.UserService;
import top.fanxfan.base.vo.UserVo;
import top.fanxfan.core.enums.StatusEnum;
import top.fanxfan.core.exception.ServiceException;
import top.fanxfan.core.tools.PageUtils;
import top.fanxfan.core.tools.SecretUtils;

import java.util.List;

import static top.fanxfan.base.constants.BaseErrorConstants.USER_NOT_EXIST_MESSAGE;

/**
 * 用户Service实现类
 *
 * @author fanxfan
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * 默认密码
     */
    private static final String DEFAULT_PASSWORD = "password";

    private final UserRepository userRepository;

    private final EntityManager entityManager;

    @Override
    public Page<User> list(UserSearchVo userSearchVo) {
        QUser user = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (ObjectUtil.isNotEmpty(userSearchVo.getUserName())) {
            booleanBuilder.and(user.userName.endsWith(userSearchVo.getUserName()));
        }
        if (ObjectUtil.isNotEmpty(userSearchVo.getStatus())) {
            booleanBuilder.and(user.status.eq(userSearchVo.getStatus()));
        }
        if (ObjectUtil.isNotEmpty(userSearchVo.getUserStatus())) {
            booleanBuilder.and(user.userStatus.eq(userSearchVo.getUserStatus()));
        }
        if (ObjectUtil.isNotEmpty(userSearchVo.getMobile())) {
            booleanBuilder.and(user.mobile.eq(userSearchVo.getMobile()));
        }
        if (ObjectUtil.isNotEmpty(userSearchVo.getEmail())) {
            booleanBuilder.and(user.email.eq(userSearchVo.getEmail()));
        }
        // 创建Pageable对象，设置页数和每页显示的记录数
        return userRepository.findAll(booleanBuilder, PageUtils.page(userSearchVo));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean add(UserVo userVo) {
        Assert.notNull(userVo, "用户信息不能为空");
        User build = User.builder()
                .userName(userVo.getUserName())
                .userType(userVo.getUserType())
                .mobile(userVo.getMobile())
                .email(userVo.getEmail())
                .userStatus(userVo.getUserStatus())
                .password(SecretUtils.encrypt(DEFAULT_PASSWORD))
                .build();
        userRepository.saveAndFlush(build);
        return true;
    }

    @Override
    public void initAdmin() {
        // 是否已经存在管理员账号
        List<User> byUserType = userRepository.findByUserType(UserTypeEnum.ADMIN);
        if (byUserType.isEmpty()) {
            User build = User.builder()
                    .userName("admin")
                    .userType(UserTypeEnum.ADMIN)
                    .mobile("13800000000")
                    .email("admin@aliyun.com")
                    .password(SecretUtils.encrypt("fanxfan"))
                    .status(StatusEnum.SHOW)
                    .userStatus(0)
                    .build();
            userRepository.saveAndFlush(build);
        }
    }

    @Override
    public User infoById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ServiceException(USER_NOT_EXIST_MESSAGE));
    }

    @Override
    public Boolean update(UserVo userVo) {
        Assert.notNull(userVo, "用户信息不能为空");
        User user = userRepository.findById(userVo.getId()).orElseThrow(() -> new ServiceException(USER_NOT_EXIST_MESSAGE));
        BeanUtil.copyProperties(userVo, user);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public Boolean resetPassword(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(USER_NOT_EXIST_MESSAGE));
        user.setPassword(SecretUtils.encrypt(DEFAULT_PASSWORD));
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public Boolean deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(USER_NOT_EXIST_MESSAGE));
        if (user.getUserType().equals(UserTypeEnum.SUPER)) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    @Override
    public Boolean status(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(USER_NOT_EXIST_MESSAGE));
        user.setUserStatus(user.getUserStatus() == 0 ? 1 : 0);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public void initSuper() {
        // 是否已经存在超级管理员账号
        List<User> byUserType = userRepository.findByUserType(UserTypeEnum.SUPER);
        long count = userRepository.count();
        if (byUserType.isEmpty() && count == 0) {
            User build = User.builder()
                    .userName("super")
                    .userType(UserTypeEnum.SUPER)
                    .mobile("15800000000")
                    .email("fanxfan@aliyun.com")
                    .password(SecretUtils.encrypt("fanxfan"))
                    .status(StatusEnum.SHOW)
                    .userStatus(0)
                    .build();
            userRepository.saveAndFlush(build);
        }
    }
}
