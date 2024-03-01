package top.fanxfan.jpa.repository;

import org.springframework.stereotype.Repository;
import top.fanxfan.jpa.core.repository.BaseRepository;
import top.fanxfan.jpa.entity.exam.QuestionCatalog;

/**
 * 试题分类Repository
 *
 * @author fanxfan
 */
@Repository
public interface QuestionCatalogRepository extends BaseRepository<QuestionCatalog> {
}
