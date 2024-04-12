package top.fanxfan.exam.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 * 考试试题Vo
 *
 * @author fanxfan
 */
@Getter
@Setter
@Validated
public class ExamQuestionVo {

    /**
     * 试题
     */
    @NotEmpty(message = "试题不能为空")
    @NotNull(message = "试题不能为空")
    private Long questionId;

    /**
     * 分数
     */
    @NotNull(message = "分数不能为空")
    private Integer score = 1;

    /**
     * 限时时间，单位秒
     */
    private Integer time = 10;

    /**
     * 顺序号
     */
    private Integer orderNum = 0;
}
