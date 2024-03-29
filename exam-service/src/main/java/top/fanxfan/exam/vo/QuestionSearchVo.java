package top.fanxfan.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import top.fanxfan.core.entity.BaseSearch;
import top.fanxfan.exam.entity.QuestionCatalog;
import top.fanxfan.exam.enums.QuestionDegreeEnum;
import top.fanxfan.exam.enums.QuestionTypeEnum;

/**
 * 试题搜索
 *
 * @author fanxfan
 */
@Getter
@Setter
@Schema(description = "试题搜索", name = "QuestionSearchVo", implementation = BaseSearch.class)
public class QuestionSearchVo extends BaseSearch {
    /**
     * 试题类型 {@link QuestionTypeEnum}
     */
    @Schema(description = "试题类型", name = "type")
    private QuestionTypeEnum type;

    /**
     * 难度 {@link QuestionDegreeEnum}
     */
    @Schema(description = "难度", name = "degree")
    private QuestionDegreeEnum degree;

    /**
     * 题库 {@link QuestionCatalog}
     */
    @Schema(description = "题库", name = "catalog")
    private Long catalogId;

    /**
     * 关键字
     */
    @Schema(description = "关键字", name = "keyword")
    private String keyword;
}
