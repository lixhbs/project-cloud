package com.chengyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengyu.enums.BizCodeEnum;
import com.chengyu.mapper.ProjectMapper;
import com.chengyu.model.ProjectDO;
import com.chengyu.request.ProjectRequest;
import com.chengyu.service.ProjectService;
import com.chengyu.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-14 15:48
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public JsonData add(ProjectRequest projectRequest)
    {
        String name = projectRequest.getName();
        if(checkUnique(name)){
            ProjectDO projectDO = new ProjectDO();
            BeanUtils.copyProperties(projectRequest, projectDO);
            int insert = projectMapper.insert(projectDO);
            if(insert > 0){
                return JsonData.buildSuccess(projectDO);
            }
        } else{
            return JsonData.buildResult(BizCodeEnum.PROJECT_REPEAT);
        }
        return JsonData.buildError("项目添加失败");
    }

    public boolean checkUnique(String name){
        List<ProjectDO> list = projectMapper.selectList(new QueryWrapper<ProjectDO>().eq("name", name));
        return list == null || list.size() <= 0;
    }
}
