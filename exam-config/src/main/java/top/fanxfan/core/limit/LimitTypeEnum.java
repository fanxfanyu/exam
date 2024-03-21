package top.fanxfan.core.limit;

/**
 * @author fanxfan
 */
public enum LimitTypeEnum {

    /**
     * 全局限流
     */
    DEFAULT,

    /**
     * ip限流
     */
    IP,

    /**
     * 用户限流
     */
    USER,

    /**
     * 集群限流
     */
    CLUSTER

}
