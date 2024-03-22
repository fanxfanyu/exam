package top.fanxfan.core.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    /**
     * 展示
     */
    SHOW(0, "展示"),
    /**
     * 隐藏
     */
    HIDDEN(1, "隐藏");

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名称
     */
    private final String name;

    public static StatusEnum getByValue(Integer value) {
        return ArrayUtil.firstMatch((obj) -> obj.getValue().equals(value), values());
    }
}
