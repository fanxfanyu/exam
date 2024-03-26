package top.fanxfan.exam.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import top.fanxfan.exam.enums.QuestionDegreeEnum;
import top.fanxfan.exam.enums.QuestionTypeEnum;

import java.util.List;

/**
 * 试题VO
 *
 * @author fanxfan
 */
@Getter
@Setter
public class QuestionVo {

    /**
     * 题目id
     */
    private Long id;
    /**
     * 题目
     */
    @NotEmpty(message = "题目不能为空")
    private String title;
    /**
     * 题目类型
     */
    @NotNull(message = "题目类型不能为空")
    private QuestionTypeEnum type;
    /**
     * 难度
     */
    @NotNull(message = "题目难度不能为空")
    private QuestionDegreeEnum degree;
    /**
     * 所属目录id
     */
    private Long catalogId;
    /**
     * 解析
     */
    private String analysis;
    /**
     * 答案
     */
    private String answer;
    /**
     * 子问题
     */
    private List<QuestionVo> children;
    /**
     * 选项
     */
    @NotNull(message = "选项不能为空")
    private List<QuestionOptionVo> options;
}
