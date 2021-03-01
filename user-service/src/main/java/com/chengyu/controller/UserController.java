package com.chengyu.controller;


import com.chengyu.exception.BizException;
import com.chengyu.model.UserDO;
import com.chengyu.service.UserService;
import com.chengyu.util.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录表 前端控制器
 * </p>
 *
 * @author Lix
 * @since 2021-02-25
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/api/user/v1")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("userDetil/{userid}")
    public JsonData userDetil(@PathVariable("userid") String userId)
    {
        UserDO detail = userService.detail(userId);
        return JsonData.buildSuccess(detail);
    }
}

