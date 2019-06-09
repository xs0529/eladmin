package me.zhengjie.modules.article.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.modules.article.service.ArticleService;
import me.zhengjie.modules.article.service.dto.ArticleQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* @author xieshuang
* @date 2019-06-09
*/
@RestController
@RequestMapping("api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Log("查询Article")
    @GetMapping(value = "/article")
    @PreAuthorize("hasAnyRole('ADMIN','ARTICLE_ALL','ARTICLE_SELECT')")
    public ResponseEntity getArticles(ArticleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(articleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("查询Article")
    @GetMapping(value = "/article/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ARTICLE_ALL','ARTICLE_SELECT')")
    public ResponseEntity getArticles(@PathVariable Long id){
        return new ResponseEntity(articleService.findById(id),HttpStatus.OK);
    }

    @Log("新增Article")
    @PostMapping(value = "/article")
    @PreAuthorize("hasAnyRole('ADMIN','ARTICLE_ALL','ARTICLE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Article resources){
        return new ResponseEntity(articleService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Article")
    @PutMapping(value = "/article")
    @PreAuthorize("hasAnyRole('ADMIN','ARTICLE_ALL','ARTICLE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Article resources){
        articleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Article")
    @DeleteMapping(value = "/article/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ARTICLE_ALL','ARTICLE_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        articleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}