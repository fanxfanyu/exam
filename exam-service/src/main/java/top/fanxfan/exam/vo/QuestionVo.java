package top.fanxfan.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "试题VO", name = "question")
public class QuestionVo {

    /**
     * 题目id
     */
    @Schema(description = "题目id", name = "id")
    private Long id;

    /**
     * 题目
     */
    @Schema(description = "题目", name = "title")
    @NotEmpty(message = "题目不能为空")
    private String title;

    /**
     * 题目类型
     */
    @Schema(description = "题目类型", name = "type")
    @NotNull(message = "题目类型不能为空")
    private QuestionTypeEnum type;

    /**
     * 难度
     */
    @Schema(description = "题目难度", name = "degree")
    @NotNull(message = "题目难度不能为空")
    private QuestionDegreeEnum degree;

    /**
     * 所属目录id
     */
    @Schema(description = "题目所属目录id", name = "catalogId")
    private Long catalogId;

    /**
     * 解析
     */
    @Schema(description = "题目解析", name = "analysis")
    private String analysis;

    /**
     * 答案
     */
    @Schema(description = "题目答案", name = "answer")
    private String answer;

    /**
     * 子问题
     */
    @Schema(description = "子问题", name = "children")
    private List<QuestionVo> children;

    /**
     * 选项
     */
    @Schema(description = "选项", name = "options")
    @NotNull(message = "选项不能为空")
    private List<QuestionOptionVo> options;
}
