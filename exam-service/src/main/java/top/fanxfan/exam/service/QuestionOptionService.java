package top.fanxfan.exam.service;

/**
 * 试题选项Service
 *
 * @author fanxfan
 */
public interface QuestionOptionService {
    /**
     * 删除试题选项
     *
     * @param id 试题选项ID
     * @return 是否删除
     */
    Boolean remove(Long id);
}
