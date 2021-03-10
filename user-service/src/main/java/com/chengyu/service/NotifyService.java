package com.chengyu.service;

import com.chengyu.enums.SendCodeEnum;
import com.chengyu.util.JsonData;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-01 10:59
 */
public interface NotifyService
{
    /**
     * 发送邮件验证码
     * @author Lix.
     * @param sendCodeType 验证码类型
     * @param to 收件人
     * @return JsonData
     * @date 2021/3/1 11:00
     */
    JsonData sendCode(SendCodeEnum sendCodeType, String to);

    /**
     * 校验图形验证码
     * @author Lix.
     * @param sendCodeEnum sendCodeEnum 验证码业务类型
     * @param to to 接收者
     * @param code code 待验证的验证码
     * @return boolean
     * @date 2021/3/10 07:56
     */
    boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code);
}
