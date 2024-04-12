package top.fanxfan.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 选项结果
 *
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum QuestionResult {

    /**
     * 正确
     */
    RIGHT("正确"),
    /**
     * 错误
     */
    WRONG("错误");


    /**
     * 名称
     */
    private final String name;
}
