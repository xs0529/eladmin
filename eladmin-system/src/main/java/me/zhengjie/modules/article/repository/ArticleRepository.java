package me.zhengjie.modules.article.repository;

import me.zhengjie.modules.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author xieshuang
* @date 2019-06-09
*/
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor {
}