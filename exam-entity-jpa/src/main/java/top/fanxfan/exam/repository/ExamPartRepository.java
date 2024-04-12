package top.fanxfan.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.core.repository.BaseRepository;
import top.fanxfan.exam.entity.ExamPart;

/**
 * 考试题目repository
 *
 * @author fanxfan
 */
@Repository
public interface ExamPartRepository extends BaseRepository<ExamPart> {
}
