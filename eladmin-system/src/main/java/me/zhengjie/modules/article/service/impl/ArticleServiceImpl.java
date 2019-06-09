package me.zhengjie.modules.article.service.impl;

import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.article.repository.ArticleRepository;
import me.zhengjie.modules.article.service.ArticleService;
import me.zhengjie.modules.article.service.dto.ArticleDTO;
import me.zhengjie.modules.article.service.dto.ArticleQueryCriteria;
import me.zhengjie.modules.article.service.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

/**
* @author xieshuang
* @date 2019-06-09
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Object queryAll(ArticleQueryCriteria criteria, Pageable pageable){
        Page<Article> page = articleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(articleMapper::toDto));
    }

    @Override
    public Object queryAll(ArticleQueryCriteria criteria){
        return articleMapper.toDto(articleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ArticleDTO findById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        ValidationUtil.isNull(article,"Article","id",id);
        return articleMapper.toDto(article.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleDTO create(Article resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setId(snowflake.nextId());
        resources.setUsername(SecurityUtils.getUsername());
        resources.setCreatTime(new Timestamp(System.currentTimeMillis()));
        resources.setModifyTime(new Timestamp(System.currentTimeMillis()));
        return articleMapper.toDto(articleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Article resources) {
        Optional<Article> optionalArticle = articleRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalArticle,"Article","id",resources.getId());

        Article article = optionalArticle.get();
        // 此处需自己修改
        resources.setId(article.getId());
        resources.setCreatTime(article.getCreatTime());
        resources.setModifyTime(new Timestamp(System.currentTimeMillis()));
        resources.setUsername(article.getUsername());
        articleRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}