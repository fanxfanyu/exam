package top.fanxfan.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 试题类型
 *
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum QuestionTypeEnum {

    /**
     * 单选
     */
    SINGLE_CHOICE("单选"),

    /**
     * 多选
     */
    MULTIPLE_CHOICE("多选"),

    /**
     * 简答
     */
    SHORT_ANSWER("简答"),

    /**
     * 判断
     */
    JUDGMENT("判断"),

    /**
     * 材料
     */
    MATERIALS("材料"),

    /**
     * 无效
     */
    DEFAULT_TYPE("无效");

    /**
     * 名称
     */
    private final String name;

}
