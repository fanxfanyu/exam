package top.fanxfan.exam.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 试题分类VO
 *
 * @author fanxfan
 */
@Getter
@Setter
public class QuestionCatalogVo {

    /**
     * 分类ID
     */
    private Long id;
    /*
     * 分类名称
     */
    @NotEmpty(message = "分类名称不能为空")
    private String name;

    /*
     * 排序
     */
    @Min(value = 0, message = "排序必须大于等于0")
    private Integer sequence;

    /**
     * 父级分类ID
     */
    private Long parentId;
}
