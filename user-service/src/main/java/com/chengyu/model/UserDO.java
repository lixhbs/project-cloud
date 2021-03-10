package com.chengyu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户 
 * </p>
 *
 * @author Lix
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 乐观锁
     */
    private Integer revision;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

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
     * 密码
     */
    private String pwd;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 状态 0、删除；1、正常
     */
    private Integer status;

    /**
     * 微信标识
     */
    private String openid;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 性别 0表示女，1表示男
     */
    private Integer sex;

    /**
     * 用户个人性签名
     */
    private String slogan;


}
