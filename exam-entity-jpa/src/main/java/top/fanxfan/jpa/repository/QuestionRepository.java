package top.fanxfan.jpa.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.jpa.core.repository.BaseRepository;
import top.fanxfan.jpa.entity.exam.Question;

/**
 * 试题Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionRepository extends BaseRepository<Question> {
}
