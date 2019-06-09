package me.zhengjie.modules.article.service;

import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.modules.article.service.dto.ArticleDTO;
import me.zhengjie.modules.article.service.dto.ArticleQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author xieshuang
* @date 2019-06-09
*/
@CacheConfig(cacheNames = "article")
public interface ArticleService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(ArticleQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ArticleQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ArticleDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ArticleDTO create(Article resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Article resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}