package top.fanxfan.core.tools;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import top.fanxfan.core.entity.BaseSearch;

/**
 * 分页工具类
 *
 * @author fanxfan
 */
public class PageUtils {

    private PageUtils() {
    }

    /**
     * 构造分页对象
     *
     * @param baseSearch 查询条件
     * @return 返回分页对象
     */
    public static PageRequest page(BaseSearch baseSearch) {
        return PageRequest.of(baseSearch.getPage(), baseSearch.getPageSize(), Sort.by("createDate").ascending());
    }
}
