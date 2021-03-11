package com.chengyu.service.impl;

import com.chengyu.constant.CacheKey;
import com.chengyu.enums.BizCodeEnum;
import com.chengyu.enums.SendCodeEnum;
import com.chengyu.component.MailService;
import com.chengyu.service.NotifyService;
import com.chengyu.util.CheckUtil;
import com.chengyu.util.CommonUtil;
import com.chengyu.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    public static final String SUBJECT = "应用保障";

    public static final String CONTENT = "您好，您的验证码为：%s，有效时间10分钟。";

    /**
     * 10 分钟有效
     *
     * @date 2021/3/1 14:55
     */
    public static final int CODE_EXPIRED = 60 * 1000 * 10;

    public static final int TIME_INTERVAL = 60 * 1000;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to)
    {
        String cacheKey = String.format(CacheKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);
        String cacheValue = redisTemplate.opsForValue().get(cacheKey);
        // 判断是否重复发送
        if (StringUtils.isNotEmpty(cacheValue))
        {
            long ttl = Long.parseLong(cacheValue.split("_")[1]);
            if ((System.currentTimeMillis() - ttl) < TIME_INTERVAL)
            {
                log.error("重复发送验证码，时间间隔:{}s", (System.currentTimeMillis() - ttl) / 1000);
                return JsonData.buildResult(BizCodeEnum.CODE_LIMITED);
            }
        }
        // 拼接验证码
        String randomCode = CommonUtil.getRandomCode(6);
        String value = randomCode + "_" + CommonUtil.getCurrentTimestamp();

        redisTemplate.opsForValue().set(cacheKey, value, CODE_EXPIRED, TimeUnit.MILLISECONDS);
        if (CheckUtil.isEmail(to))
        {
            //邮箱验证码
            mailService.sendSimpleMail(to, SUBJECT, emailHtml.replace("${type}", "账号注册").replace("${code}", randomCode));
            return JsonData.buildSuccess();
        } else if (CheckUtil.isPhone(to))
        {
            //短信验证码
            log.info("手机验证码");
        }
        return JsonData.buildResult(BizCodeEnum.CODE_TO_ERROR);

    }

    /**
     * 校验图形验证码
     * @author Lix.
     * @param sendCodeEnum sendCodeEnum 验证码业务类型
     * @param to to 接收者
     * @param code code 待验证的验证码
     * @return boolean
     * @date 2021/3/10 07:56
     */
    @Override
    public boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code)
    {
        String cacheKey = String.format(CacheKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);
        String cacheValue = redisTemplate.opsForValue().get(cacheKey);
        if(StringUtils.isNotBlank(cacheValue)){
            String cacheCode = cacheValue.split("_")[0];
            if(cacheCode.equals(code)){
                redisTemplate.delete(cacheKey);
                return true;
            }
        }
        return false;
    }

    private static String emailHtml = "<html><head>\n" +
            "    <base target=\"_blank\" />\n" +
            "    <style type=\"text/css\">::-webkit-scrollbar{ display: none; }</style>\n" +
            "    <style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style>\n" +
            "    <style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style>\n" +
            "    <style type=\"text/css\">\n" +
            "        body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
            "        td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
            "        pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
            "        th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
            "        img{ border:0}\n" +
            "        header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
            "        blockquote{margin-right:0px}\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body tabindex=\"0\" role=\"listitem\">\n" +
            "<table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "        <td>\n" +
            "            <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\n" +
            "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\">\n" +
            "                    <tbody><tr><td width=\"210\"></td></tr></tbody>\n" +
            "                </table>\n" +
            "            </div>\n" +
            "            <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\n" +
            "                <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\n" +
            "                    <strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span style=\"color:#f60;font-size: 16px;\"></span>您好！</strong>\n" +
            "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
            "                        您正在进行<span style=\"color: red\">${type}</span>操作，请在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">${code}</span>，以完成操作。\n" +
            "                    </strong>\n" +
            "                </div>\n" +
            "                <div style=\"margin-bottom:30px;\">\n" +
            "                    <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\n" +
            "                        <p style=\"color:#747474;\">\n" +
            "                            注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请及时登录并修改密码以保证帐户安全\n" +
            "                            <br>（工作人员不会向你索取此验证码，请勿泄漏！)\n" +
            "                        </p>\n" +
            "                    </small>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"width:700px;margin:0 auto;\">\n" +
            "                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
            "                    <p>此为系统邮件，请勿回复<br>\n" +
            "                        请保管好您的邮箱，避免账号被他人盗用\n" +
            "                    </p>\n" +
            "                    <p>应用保障技术团队</p>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n" +
            "</body></html>";
}
