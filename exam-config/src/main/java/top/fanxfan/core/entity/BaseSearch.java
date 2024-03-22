package top.fanxfan.core.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础查询类
 * <p>主要限制和操作页数和分页数量</p>
 *
 * @author fanxfan
 */
@Getter
@Setter
public abstract class BaseSearch {

    /**
     * 查询页数
     */
    @Min(value = 0, message = "查询页数不能小于0")
    @Schema(description = "查询页数", defaultValue = "0", example = "0", minimum = "0")
    private Integer page;

    /**
     * 查询数量
     */
    @Min(value = 0, message = "查询数量不能小于0")
    @Max(value = 100, message = "查询数量不能大于100")
    @Schema(description = "查询数量", defaultValue = "10", example = "10", minimum = "1", maximum = "100")
    private Integer pageSize;


}
