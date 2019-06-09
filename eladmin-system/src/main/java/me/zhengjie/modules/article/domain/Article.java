package me.zhengjie.modules.article.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author xieshuang
* @date 2019-06-09
*/
@Entity
@Data
@Table(name="article")
public class Article implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 文章类型
     */
    @Column(name = "article_type")
    private Long articleType;

    /**
     * 文章内容
     */
    @Column(name = "article_content")
    private String articleContent;

    /**
     * 文章标题
     */
    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "creat_time")
    private Timestamp creatTime;

    @Column(name = "modify_time")
    private Timestamp modifyTime;

    /**
     * 文章状态
     */
    @Column(name = "article_status")
    private Long articleStatus;

    /**
     * 创建人id
     */
    @Column(name = "create_user")
    private Long createUser;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 文章来源（转载时的url）
     */
    @Column(name = "article_source")
    private String articleSource;

    /**
     * 来源类型
     */
    @Column(name = "source_type")
    private Long sourceType;
}