package top.fanxfan.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.exam.entity.QuestionOption;
import top.fanxfan.core.repository.BaseRepository;

/**
 * 试题选项Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionOptionRepository extends BaseRepository<QuestionOption> {
}
