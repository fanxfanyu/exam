package top.fanxfan.exam.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import top.fanxfan.exam.enums.TimeModel;

import java.util.Date;
import java.util.List;

/**
 * 考试Vo
 *
 * @author fanxfan
 */
@Data
@Validated
public class ExamVo {

    /**
     * 考试id
     */
    private Long id;

    /**
     * 考试名称
     */
    @NotEmpty(message = "考试名称不能为空")
    private String name;

    /**
     * 时间模式
     */
    @NotNull(message = "时间模式不能为空")
    private TimeModel timeMode;

    /**
     * 限时时长
     */
    private Integer time;

    /**
     * 考试开始时间
     */
    private Date startTime;
    /**
     * 考试结束时间
     */
    private Date endTime;

    /**
     * 考试组成部分
     */
    private List<ExamPartVo> parts;
}
