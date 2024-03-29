package top.fanxfan.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "试题选项VO", name = "QuestionOptionVo")
public class QuestionOptionVo {

    /**
     * 选项内容
     */
    @Schema(description = "选项内容", name = "content")
    private String content;

    /**
     * 选项结果
     */
    @Schema(description = "选项结果", name = "result")
    private QuestionResult result;

}
