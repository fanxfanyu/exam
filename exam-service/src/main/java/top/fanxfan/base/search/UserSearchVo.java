package top.fanxfan.base.search;

import lombok.Getter;
import lombok.Setter;
import top.fanxfan.core.entity.BaseSearch;
import top.fanxfan.core.enums.StatusEnum;

/**
 * @author fanxfan
 */
@Getter
@Setter
public class UserSearchVo extends BaseSearch {

    /**
     * 用户名
     */
    private String userName;

    /*
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态
     */
    private Integer userStatus;

    /**
     * 展示状态
     */
    private StatusEnum status;
}
