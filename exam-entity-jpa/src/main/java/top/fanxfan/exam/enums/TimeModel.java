package top.fanxfan.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间模式
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum TimeModel {

    /**
     * 限时模式
     */
    LIMITED_MODE("限时模式"),

    /**
     * 无限模式
     */
    UNLIMITED_MODE("无限模式"),

    /**
     * 竞速模式
     */
    SPEED_MODE("竞速模式"),

    /**
     * 单题限时模式
     */
    SINGLE_LIMITED_MODE("单题限时模式");

    /**
     * 描述
     */
    private final String name;


}
