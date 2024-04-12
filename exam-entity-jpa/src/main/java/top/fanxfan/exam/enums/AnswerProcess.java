package top.fanxfan.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 答题进程
 *
 * @author fanxfan
 */
@Getter
@AllArgsConstructor
public enum AnswerProcess {

    /**
     * 生成
     */
    GENERATE("生成"),

    /**
     * 答题
     */
    ANSWER("答题"),

    /**
     * 提交
     */
    SUBMIT("提交"),

    /**
     * 批改
     */
    REVIEW("批改"),

    /**
     * 完成
     */
    FINISH("完成");

    /**
     * 名称
     */
    private final String name;


}
