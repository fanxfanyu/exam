package top.fanxfan.exam.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import top.fanxfan.core.entity.BaseSearch;

import java.util.Date;

/**
 * 考试搜索条件
 *
 * @author fanxfan
 */
@Getter
@Setter
@Validated
public class ExamSearch extends BaseSearch {

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 关键字
     */
    private String keyword;
}
