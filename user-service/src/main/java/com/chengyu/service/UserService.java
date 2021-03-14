package com.chengyu.service;

import com.chengyu.model.UserDO;
import com.chengyu.request.UserLoginRequest;
import com.chengyu.request.UserRegisterRequest;
import com.chengyu.util.JsonData;

/**
 * @author li
 */
public interface UserService
{
    /**
     * 查询用户详情
     * @author Lix.
     * @param id id
     * @return UserDO UserDO
     * @date 2021/3/14 13:13
     */
    UserDO detail(String id);

    /**
     * 用户注册
     * @author Lix.
     * @param registerRequest registerRequest
     * @return JsonData
     * @date 2021/3/14 13:13
     */
    JsonData register(UserRegisterRequest registerRequest);

    /**
     * 用户登录
     * @author Lix.
     * @param loginRequest loginRequest
     * @return JsonData
     * @date 2021/3/14 13:13
     */
    JsonData login(UserLoginRequest loginRequest);
}
