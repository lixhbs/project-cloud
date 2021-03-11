package com.chengyu.controller;


import com.chengyu.enums.BizCodeEnum;
import com.chengyu.exception.BizException;
import com.chengyu.model.UserDO;
import com.chengyu.request.UserLoginRequest;
import com.chengyu.request.UserRegisterRequest;
import com.chengyu.service.FileService;
import com.chengyu.service.UserService;
import com.chengyu.util.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FileService fileService;

    @GetMapping("/userDetil/{userid}")
    public JsonData userDetil(@PathVariable("userid") String userId)
    {
        UserDO detail = userService.detail(userId);
        return JsonData.buildSuccess(detail);
    }

    /**
     * 上传用户头像
     * <p>
     * 默认文件大小 1M,超过会报错
     *
     * @param file 文件上传
     * @return JsonData
     */
    @ApiOperation("用户头像上传")
    @PostMapping(value = "/upload")
    public JsonData uploadHeaderImg(@ApiParam(value = "文件上传", required = true) @RequestPart("file") MultipartFile file)
    {
        String result = fileService.uploadUserHeadImg(file);
        return result != null ? JsonData.buildSuccess(result) : JsonData.buildResult(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL);
    }

    /**
     * 用户注册
     *
     * @param registerRequest 注册对象
     * @return JsonData
     * @author Lix.
     * @date 2021/3/7 09:35
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public JsonData register(@ApiParam("用户注册对象") @RequestBody UserRegisterRequest registerRequest)
    {
        JsonData register = userService.register(registerRequest);
        return JsonData.buildSuccess(register);
    }

    /**
     * 登录
     * @param loginRequest
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("login")
    public JsonData register(@RequestBody UserLoginRequest loginRequest){

        JsonData jsonData = userService.login(loginRequest);
        return jsonData;
    }


}

