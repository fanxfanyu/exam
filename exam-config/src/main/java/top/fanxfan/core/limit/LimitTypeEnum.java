package top.fanxfan.core.limit;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 限流类型
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum LimitTypeEnum {

    /**
     * 全局限流
     */
    DEFAULT("全局限流"),

    /**
     * ip限流
     */
    IP("ip限流"),

    /**
     * 用户限流
     */
    USER("用户限流"),

    /**
     * 集群限流
     */
    CLUSTER("集群限流");

    /**
     * 名称
     */
    private final String name;

}
