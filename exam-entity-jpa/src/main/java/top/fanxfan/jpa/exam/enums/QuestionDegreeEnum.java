package top.fanxfan.jpa.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 试题难度
 *
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum QuestionDegreeEnum {

    /**
     * 简单
     */
    EASY(0, "简单"),

    /**
     * 中等
     */
    INTERMEDIATE(1, "中等"),

    /**
     * 困难
     */
    HARD(2, "困难");

    /**
     * 值
     */
    private final Integer value;

    /**
     * 名称
     */
    private final String name;

}
