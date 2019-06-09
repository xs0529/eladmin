package me.zhengjie.modules.article.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import me.zhengjie.annotation.Query;

/**
* @author xieshuang
* @date 2019-06-09
*/
@Data
public class ArticleQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String articleTitle;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    // 等于
    @Query(type = Query.Type.EQUAL)
    private Long articleType;

    // 等于
    @Query(type = Query.Type.EQUAL)
    private Long articleStatus;

    // 等于
    @Query(type = Query.Type.EQUAL)
    private Long sourceType;
}