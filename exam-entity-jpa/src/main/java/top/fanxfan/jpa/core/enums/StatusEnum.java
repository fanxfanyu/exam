package top.fanxfan.jpa.core.enums;

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
}
