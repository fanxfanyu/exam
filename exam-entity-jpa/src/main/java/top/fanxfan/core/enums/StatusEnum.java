package top.fanxfan.core.enums;

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
    SHOW("展示"),
    /**
     * 隐藏
     */
    HIDDEN("隐藏");

    /**
     * 名称
     */
    private final String name;
}
