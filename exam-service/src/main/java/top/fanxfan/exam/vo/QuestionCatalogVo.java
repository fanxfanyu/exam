package top.fanxfan.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "试题分类VO", name = "QuestionCatalogVo")
public class QuestionCatalogVo {

    /**
     * 分类ID
     */
    @Schema(description = "分类ID", name = "id")
    private Long id;
    /*
     * 分类名称
     */
    @Schema(description = "分类名称", name = "name")
    @NotEmpty(message = "分类名称不能为空")
    private String name;

    /*
     * 排序
     */
    @Schema(description = "排序", name = "sequence")
    @Min(value = 0, message = "排序必须大于等于0")
    private Integer sequence;

    /**
     * 父级分类ID
     */
    @Schema(description = "父级分类ID", name = "parentId")
    private Long parentId;
}
