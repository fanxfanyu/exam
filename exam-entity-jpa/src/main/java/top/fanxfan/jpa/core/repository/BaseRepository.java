package top.fanxfan.jpa.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 通用
 *
 * @author fanxfan
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {
}
