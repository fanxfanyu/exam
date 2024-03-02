package top.fanxfan.jpa.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.jpa.core.repository.BaseRepository;
import top.fanxfan.jpa.exam.entity.QuestionOption;

/**
 * 试题选项Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionOptionRepository extends BaseRepository<QuestionOption> {
}
