package top.fanxfan.jpa.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.jpa.core.repository.BaseRepository;
import top.fanxfan.jpa.exam.entity.Question;

/**
 * 试题Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionRepository extends BaseRepository<Question> {
}
