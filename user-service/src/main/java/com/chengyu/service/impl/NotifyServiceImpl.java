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

    public static final String SUBJECT = "项目管理系统";

    public static final String CONTENT = "您的验证码为：%s，有效时间10分钟。";

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
