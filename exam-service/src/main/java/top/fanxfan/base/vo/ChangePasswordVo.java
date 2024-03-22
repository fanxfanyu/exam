package top.fanxfan.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 修改密码
 *
 * @author fanxfan
 */
@Data
@AllArgsConstructor
public class ChangePasswordVo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 新密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;
}
