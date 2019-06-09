package me.zhengjie.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * sm.ms图床
 *
 * @author jie
 * @date 2018-12-27
 */
@Data
@Entity
@Table(name = "file")
public class FileInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String url;

    private String size;

    private String height;

    private String width;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "upload_type")
    private Long uploadType;

    /**
     * delete URl
     */
    @Column(name = "delete_url")
    private String delete;

    private String username;

    @Column(name = "old_name")
    private String oldName;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

}
