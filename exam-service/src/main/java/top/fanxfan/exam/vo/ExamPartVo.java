package top.fanxfan.exam.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import top.fanxfan.exam.enums.PartModel;
import top.fanxfan.exam.enums.QuestionTypeEnum;

import java.util.List;

/**
 * 考试部分Vo
 *
 * @author fanxfan
 */
@Getter
@Setter
@Validated
public class ExamPartVo {

    /**
     * 名称
     */
    private String name;


    /**
     * 顺序
     */
    private Integer orderNum = 0;

    /**
     * 考试模式
     */
    private PartModel model = PartModel.SELECTION_MODE;

    // 随机模式使用
    /**
     * 总题数
     */
    private Integer totalSum;

    /**
     * 每题分数
     */
    private Integer itemScore;

    /**
     * 试题类型
     */
    private QuestionTypeEnum questionType;

    // 选题模式使用
    /**
     * 试题
     */
    private List<ExamQuestionVo> questions;

}
