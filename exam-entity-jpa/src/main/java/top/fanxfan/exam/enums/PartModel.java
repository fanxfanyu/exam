package top.fanxfan.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 考试模式
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum PartModel {

    /**
     * 选题模式
     */
    SELECTION_MODE("选题模式"),

    /**
     * 随机模式
     */
    STOCHASTIC_MODE("随机模式");

    /**
     * 模式描述
     */
    private final String desc;
}
