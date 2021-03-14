package com.chengyu.controller;

import com.chengyu.request.ProjectRequest;
import com.chengyu.service.ProjectService;
import com.chengyu.util.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-14 15:37
 */
@Api(tags = "项目")
@RestController
@RequestMapping("/api/project/v1")
@Slf4j
public class ProjectController
{
    @Autowired
    private ProjectService projectService;

    @ApiOperation("添加项目")
    @PostMapping("/add")
    public JsonData add(@ApiParam("项目对象") @RequestBody ProjectRequest projectRequest)
    {
        return projectService.add(projectRequest);
    }
}
