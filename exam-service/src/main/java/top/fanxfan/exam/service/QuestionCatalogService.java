package top.fanxfan.exam.service;

import top.fanxfan.exam.entity.QuestionCatalog;
import top.fanxfan.exam.vo.QuestionCatalogVo;

import java.util.List;

/**
 * 试题分类Service
 *
 * @author fanxfan
 */
public interface QuestionCatalogService {

    /**
     * 获取试题分类树
     *
     * @return 试题分类树
     */
    List<QuestionCatalog> tree();

    /**
     * 添加试题分类
     *
     * @param questionCatalogVo 试题分类 {@link QuestionCatalogVo}
     * @return 添加结果
     */
    Boolean add(QuestionCatalogVo questionCatalogVo);

    /**
     * 修改试题分类
     *
     * @param questionCatalogVo 试题分类 {@link QuestionCatalogVo}
     * @return 修改结果
     */
    Boolean update(QuestionCatalogVo questionCatalogVo);

    /**
     * 删除试题分类
     *
     * @param id 试题分类id
     * @return 删除结果
     */
    Boolean remove(Long id);
}
