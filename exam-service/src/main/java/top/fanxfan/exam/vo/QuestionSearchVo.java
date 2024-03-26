package top.fanxfan.exam.vo;

import lombok.Getter;
import lombok.Setter;
import top.fanxfan.core.entity.BaseSearch;
import top.fanxfan.exam.entity.QuestionCatalog;
import top.fanxfan.exam.enums.QuestionDegreeEnum;
import top.fanxfan.exam.enums.QuestionTypeEnum;

/**
 * @author fanxfan
 */
@Getter
@Setter
public class QuestionSearchVo extends BaseSearch {
    /**
     * 试题类型 {@link QuestionTypeEnum}
     */
    private QuestionTypeEnum type;

    /**
     * 难度 {@link QuestionDegreeEnum}
     */
    private QuestionDegreeEnum degree;

    /**
     * 题库 {@link QuestionCatalog}
     */
    private Long catalogId;

    /**
     * 关键字
     */
    private String keyword;
}
