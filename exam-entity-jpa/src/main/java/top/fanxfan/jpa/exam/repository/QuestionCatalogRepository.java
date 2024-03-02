package top.fanxfan.jpa.exam.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.jpa.core.repository.BaseRepository;
import top.fanxfan.jpa.exam.entity.QuestionCatalog;

/**
 * 试题分类Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionCatalogRepository extends BaseRepository<QuestionCatalog> {
}
