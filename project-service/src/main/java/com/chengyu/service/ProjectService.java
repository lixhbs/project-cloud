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
    JsonData add(ProjectRequest projectRequest);


}
