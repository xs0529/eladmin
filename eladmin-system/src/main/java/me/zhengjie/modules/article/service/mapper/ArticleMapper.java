package me.zhengjie.modules.article.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.modules.article.service.dto.ArticleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author xieshuang
* @date 2019-06-09
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

}