package com.chengyu.service.impl;

import com.chengyu.enums.BizCodeEnum;
import com.chengyu.enums.SendCodeEnum;
import com.chengyu.component.MailService;
import com.chengyu.service.NotifyService;
import com.chengyu.util.CheckUtil;
import com.chengyu.util.CommonUtil;
import com.chengyu.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-01 11:02
 */
@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService
{
    @Autowired
    private MailService mailService;

    public static final String SUBJECT = "项目管理系统";

    public static final String CONTENT = "您的验证码为：%s，有效时间5分钟。";


    @Override
    public JsonData sendCode(SendCodeEnum sendCodeType, String to)
    {
        if (CheckUtil.isEmail(to))
        {
            String randomCode = CommonUtil.getRandomCode(6);
            //邮箱验证码
            mailService.sendSimpleMail(to, SUBJECT, String.format(CONTENT, randomCode));
            return JsonData.buildSuccess();

        } else if (CheckUtil.isPhone(to))
        {
            //短信验证码
            log.info("手机验证码");
        }

        return JsonData.buildResult(BizCodeEnum.CODE_TO_ERROR);

    }
}
