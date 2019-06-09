package me.zhengjie.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.domain.FileInfo;
import me.zhengjie.domain.FileType;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.repository.PictureRepository;
import me.zhengjie.service.PictureService;
import me.zhengjie.service.dto.PictureQueryCriteria;
import me.zhengjie.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jie
 * @date 2018-12-27
 */
@Slf4j
@Service(value = "pictureService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PictureServiceImpl implements PictureService {


    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.getAddress}")
    private String getAddress;
    @Autowired
    private PictureRepository pictureRepository;

    public static final String SUCCESS = "success";

    public static final String CODE = "code";

    public static final String MSG = "msg";

    @Override
    public Object queryAll(PictureQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(pictureRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public FileInfo upload(MultipartFile multipartFile, String username) {
        /*File file = FileUtil.toFile(multipartFile);

        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("smfile", file);
        String result= HttpUtil.post(ElAdminConstant.Url.SM_MS_URL, paramMap);

        JSONObject jsonObject = JSONUtil.parseObj(result);
        FileInfo picture = null;
        if(!jsonObject.get(CODE).toString().equals(SUCCESS)){
            throw new BadRequestException(jsonObject.get(MSG).toString());
        }*/
        FileInfo fileInfo = new FileInfo();
        fileInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        fileInfo.setSize(FileUtil.getSize((int) multipartFile.getSize()));
        fileInfo.setFilename(multipartFile.getOriginalFilename());
        fileInfo.setOldName(multipartFile.getOriginalFilename());
        fileInfo.setUsername(username);
        // 文件上传的路径
        String filePath = FileType.fileType(fileInfo.getFilename());
        // fileName处理
        String fileName = UUID.randomUUID()+"."+FileUtil.getExtensionName(fileInfo.getFilename());
        if (filePath!=null){
            fileInfo.setFileType(filePath);
            fileInfo.setFilename(fileName);
            fileInfo.setUrl(getAddress+filePath+"/"+fileName);
            filePath = uploadFolder+filePath;
        }else {
            throw new BadRequestException("未知文件！");
        }
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdirs();
        }
        filePath = filePath +"/"+fileName;
        // 文件对象
        File dest = new File(filePath);
        // 创建路径
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            multipartFile.transferTo(dest);
            fileInfo.setDelete(filePath);
            pictureRepository.save(fileInfo);
            return fileInfo;
        } catch (IOException e) {
            throw new BadRequestException("文件上传失败："+e);
        }
    }

    @Override
    public FileInfo findById(Long id) {
        Optional<FileInfo> picture = pictureRepository.findById(id);
        ValidationUtil.isNull(picture,"Picture","id",id);
        return picture.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(FileInfo picture) {
        try {
            FileUtil.deleteFile(picture.getDelete());
            pictureRepository.delete(picture);
        } catch(Exception e){
            pictureRepository.delete(picture);
        }

    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            delete(findById(id));
        }
    }
}
