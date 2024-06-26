package top.fanxfan.exam.enums;

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
    EASY("简单"),

    /**
     * 中等
     */
    INTERMEDIATE("中等"),

    /**
     * 困难
     */
    HARD("困难");

    /**
     * 名称
     */
    private final String name;

}
