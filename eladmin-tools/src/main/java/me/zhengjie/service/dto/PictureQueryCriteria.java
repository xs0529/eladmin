package me.zhengjie.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * sm.ms图床
 *
 * @author jie
 * @date 2019-6-4 09:52:09
 */
@Data
public class PictureQueryCriteria{

    @Query(type = Query.Type.EQUAL)
    private String fileType;

    @Query(type = Query.Type.INNER_LIKE)
    private String oldName;
    
    @Query(type = Query.Type.INNER_LIKE)
    private String username;
}
