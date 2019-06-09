package me.zhengjie.modules.article.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/**
* @author xieshuang
* @date 2019-06-09
*/
@Data
public class ArticleDTO implements Serializable {

    // 处理精度丢失问题
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 文章类型
     */
    private Long articleType;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章标题
     */
    private String articleTitle;

    private Timestamp creatTime;

    private Timestamp modifyTime;

    /**
     * 文章状态
     */
    private Long articleStatus;

    /**
     * 创建人id
     */
    private Long createUser;

    /**
     * 用户名
     */
    private String username;

    /**
     * 文章来源（转载时的url）
     */
    private String articleSource;

    /**
     * 来源类型
     */
    private Long sourceType;
}