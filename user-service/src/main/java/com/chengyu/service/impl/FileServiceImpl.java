package com.chengyu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.chengyu.config.OssConfig;
import com.chengyu.service.FileService;
import com.chengyu.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-06 10:48
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService
{
    @Autowired
    private OssConfig ossConfig;

    @Value(value = "${spring.profiles.active}")
    private String active;

    @Override
    public String uploadUserHeadImg(MultipartFile file)
    {
        // @TODO 需要实现图片压缩！
        String originalFilename = file.getOriginalFilename();

        String bucketName = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        //创建oss对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //JDK8新特性写法，构建路径
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String folder = dtf.format(ldt);
        String fileName = CommonUtil.generateUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //在oss上创建文件夹test路径
        String newFileName = active + "/" + folder + "/" + fileName + extension;
        try {
            PutObjectResult result = ossClient.putObject(bucketName, newFileName, file.getInputStream());
            //返回访问路径
            if (null != result) {
                //https://xd-test1.oss-cn-beijing.aliyuncs.com/test/1.jpg
                String imgUrl = "https://"+bucketName+"."+endpoint+"/"+newFileName;
                return imgUrl;
            }
        } catch (Exception e) {
            log.error("上传头像失败:{}",e);
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }

        return null;
    }
}
