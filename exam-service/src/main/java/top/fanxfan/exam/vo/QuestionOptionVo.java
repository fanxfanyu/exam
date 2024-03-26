package top.fanxfan.exam.vo;

import lombok.Getter;
import lombok.Setter;
import top.fanxfan.exam.enums.QuestionResult;

/**
 * 试题选项VO
 *
 * @author fanxfan
 */
@Getter
@Setter
public class QuestionOptionVo {

    /**
     * 选项内容
     */
    private String content;

    /**
     * 选项结果
     */
    private QuestionResult result;

}
