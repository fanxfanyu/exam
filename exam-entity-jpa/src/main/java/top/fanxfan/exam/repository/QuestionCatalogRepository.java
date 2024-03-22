package top.fanxfan.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.core.repository.BaseRepository;
import top.fanxfan.exam.entity.QuestionCatalog;

/**
 * 试题分类Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionCatalogRepository extends BaseRepository<QuestionCatalog> {
}
