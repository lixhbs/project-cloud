package com.chengyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengyu.enums.BizCodeEnum;
import com.chengyu.enums.SendCodeEnum;
import com.chengyu.mapper.UserMapper;
import com.chengyu.model.UserDO;
import com.chengyu.request.UserRegisterRequest;
import com.chengyu.service.NotifyService;
import com.chengyu.service.UserService;
import com.chengyu.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lix.
 * @title
 * @program project-common
 * @description
 * @createtime 2021-02-25 16:50
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotifyService notifyService;

    @Override
    public UserDO detail(String id)
    {
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq("id", id));
    }

    @Override
    public JsonData register(UserRegisterRequest registerRequest)
    {
        // 判断验证码
        boolean checkCode = false;
        if(StringUtils.isNotBlank(registerRequest.getMail())){
            checkCode = notifyService.checkCode(SendCodeEnum.USER_REGISTER, registerRequest.getMail(), registerRequest.getCode());
        }
        if(!checkCode){
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }
        return null;
    }
}
