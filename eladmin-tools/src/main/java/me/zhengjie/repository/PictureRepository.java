package me.zhengjie.repository;

import me.zhengjie.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author jie
 * @date 2018-12-27
 */
public interface PictureRepository extends JpaRepository<FileInfo,Long>, JpaSpecificationExecutor {
}
