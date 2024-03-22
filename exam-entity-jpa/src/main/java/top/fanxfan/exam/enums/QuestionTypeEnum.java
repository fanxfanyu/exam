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
    SINGLE_CHOICE(0, "单选"),

    /**
     * 多选
     */
    MULTIPLE_CHOICE(1, "多选"),

    /**
     * 简答
     */
    SHORT_ANSWER(2, "简答"),

    /**
     * 判断
     */
    JUDGMENT(3, "判断"),

    /**
     * 材料
     */
    MATERIALS(4, "材料"),

    /**
     * 无效
     */
    DEFAULT_TYPE(-1, "无效");

    /**
     * 值
     */
    private final Integer value;

    /**
     * 名称
     */
    private final String name;

}
