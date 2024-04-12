package top.fanxfan.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.core.repository.BaseRepository;
import top.fanxfan.exam.entity.UserExamQuestion;

/**
 * 用户考试试题repository
 *
 * @author fanxfan
 */
@Repository
public interface UserExamQuestionRepository extends BaseRepository<UserExamQuestion> {

}