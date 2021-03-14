package com.chengyu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-14 13:16
 */
@Data
public class UserVO
{

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 手机号码
     */
    private String phone;


    /**
     * 角色
     */
    private Integer role;


    /**
     * 微信标识
     */
    private String openid;

    /**
     * 头像
     */
    @JsonProperty("head_img")
    private String headImg;

    /**
     * 性别 0表示女，1表示男
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 个性签名
     */
    private String slogan;
}

