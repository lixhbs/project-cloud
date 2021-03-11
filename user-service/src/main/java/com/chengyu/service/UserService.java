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
   UserDO detail(String id);

   JsonData register(UserRegisterRequest registerRequest);

    JsonData login(UserLoginRequest loginRequest);
}
