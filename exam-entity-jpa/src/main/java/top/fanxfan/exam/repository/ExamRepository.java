package top.fanxfan.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.core.repository.BaseRepository;
import top.fanxfan.exam.entity.Exam;

/**
 * 考试repository
 *
 * @author fanxfan
 */
@Repository
public interface ExamRepository extends BaseRepository<Exam> {
}
