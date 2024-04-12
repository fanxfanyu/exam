package top.fanxfan.exam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.fanxfan.core.repository.BaseRepository;
import top.fanxfan.exam.entity.Question;
import top.fanxfan.exam.enums.QuestionTypeEnum;

import java.util.List;

/**
 * 试题Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionRepository extends BaseRepository<Question> {
    /**
     * 根据类型随机获取试题
     *
     * @param type  试题类型
     * @return 试题列表
     */
    @Query("select q from Question q where q.type = ?1 order by rand()")
    List<Question> findByType(QuestionTypeEnum type);
}
