package com.chengyu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户登录表
 * </p>
 *
 * @author Lix
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class TUserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 乐观锁
     */
    private Integer revision;

    /**
     * ID
     */
    private Integer id;

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
    private String password;

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


}
