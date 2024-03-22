package top.fanxfan.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.core.repository.BaseRepository;
import top.fanxfan.exam.entity.Question;

/**
 * 试题Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionRepository extends BaseRepository<Question> {
}
