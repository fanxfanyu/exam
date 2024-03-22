package top.fanxfan.base.enums;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型
 *
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    /**
     * 普通用户
     */
    USER("user", "普通用户"),

    /**
     * 管理员
     */
    ADMIN("admin", "管理员"),

    /**
     * 超级管理员
     */
    SUPER("super", "超级管理员");

    /**
     * 值
     */
    private final String value;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 获取枚举对象
     *
     * @param value 枚举值
     * @return 枚举对象
     */
    public static UserTypeEnum getByValue(String value) {
        return ArrayUtil.firstMatch((obj) -> CharSequenceUtil.equals(obj.getValue(), value, true), values());
    }
}
