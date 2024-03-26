package top.fanxfan.exam.service;

import org.springframework.data.domain.Page;
import top.fanxfan.exam.entity.Question;
import top.fanxfan.exam.vo.QuestionSearchVo;
import top.fanxfan.exam.vo.QuestionVo;

/**
 * 试题Service
 *
 * @author fanxfan
 */
public interface QuestionService {

    /**
     * 试题列表
     *
     * @param questionSearchVo 查询参数 {@link QuestionSearchVo}
     * @return 试题列表
     */
    Page<Question> list(QuestionSearchVo questionSearchVo);

    /**
     * 添加试题
     *
     * @param questionVo 试题信息 {@link QuestionVo}
     * @return 添加结果
     */
    Boolean add(QuestionVo questionVo);

    /**
     * 修改试题
     *
     * @param id 试题ID
     * @return 修改结果
     */
    Boolean remove(Long id);

    /**
     * 修改试题
     *
     * @param questionVo 试题信息 {@link QuestionVo}
     * @return 修改结果
     */
    Boolean update(QuestionVo questionVo);

    /**
     * 获取试题
     *
     * @param id 试题ID
     * @return 试题信息 {@link Question}
     */
    Question get(Long id);
}
