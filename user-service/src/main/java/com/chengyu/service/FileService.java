package com.chengyu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-06 10:46
 */
public interface FileService
{
    /**
     * 用户头像上传
     * @author Lix.
     * @param file file
     * @return 图片地址
     * @date 2021/3/6 10:47
     */
    String uploadUserHeadImg(MultipartFile file);
}
