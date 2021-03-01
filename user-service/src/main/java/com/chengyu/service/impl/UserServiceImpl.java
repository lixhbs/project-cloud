package com.chengyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengyu.mapper.UserMapper;
import com.chengyu.model.UserDO;
import com.chengyu.service.UserService;
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
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO detail(String id)
    {
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq("id", id));
    }
}
