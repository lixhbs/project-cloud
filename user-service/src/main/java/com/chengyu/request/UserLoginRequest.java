package com.chengyu.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

@Data
@ApiModel(value = "登录对象",description = "用户登录请求对象")
public class UserLoginRequest {



    @ApiModelProperty(value = "邮箱", example = "835461226@qq.com")
    private String mail;

    @ApiModelProperty(value = "密码", example = "123456")
    private String pwd;


}
