package com.chengyu.service;

import com.chengyu.request.ProjectRequest;
import com.chengyu.util.JsonData;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-14 15:01
 */
public interface ProjectService
{
    /**
     * 新增项目
     * @author Lix.
     * @param projectRequest projectRequest
     * @return JsonData
     * @date 2021/3/14 16:43
     */
    JsonData add(ProjectRequest projectRequest);
}
