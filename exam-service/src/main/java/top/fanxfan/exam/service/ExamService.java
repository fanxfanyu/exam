package top.fanxfan.exam.service;

import org.springframework.data.domain.Page;
import top.fanxfan.exam.entity.Exam;
import top.fanxfan.exam.vo.ExamSearch;
import top.fanxfan.exam.vo.ExamVo;

/**
 * 考试Service
 *
 * @author fanxfan
 */
public interface ExamService {

    /**
     * 获取考试列表
     *
     * @param examSearch 查询条件 {@link ExamSearch}
     * @return 考试列表
     */
    Page<Exam> list(ExamSearch examSearch);

    /**
     * 获取考试
     *
     * @param id 考试id
     * @return 考试
     */
    Exam get(Long id);

    /**
     * 新增考试
     *
     * @param id 考试id
     * @return 是否成功
     */
    Boolean remove(Long id);

    /**
     * 新增考试
     *
     * @param examVo 考试信息 {@link ExamVo}
     * @return 是否成功
     */
    Boolean createWithAll(ExamVo examVo);

    /**
     * 仅新增基础信息
     *
     * @param examVo 考试信息 {@link ExamVo}
     * @return 考试
     */
    Exam createOnlyBase(ExamVo examVo);

    /**
     * 更新考试
     *
     * @param examVo 考试信息 {@link ExamVo}
     * @return 是否成功
     */
    Boolean updateOnly(ExamVo examVo);
}
