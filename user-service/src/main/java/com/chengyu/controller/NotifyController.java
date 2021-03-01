package com.chengyu.controller;

import com.chengyu.enums.BizCodeEnum;
import com.chengyu.enums.SendCodeEnum;
import com.chengyu.service.NotifyService;
import com.chengyu.util.CommonUtil;
import com.chengyu.util.JsonData;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lix.
 * @title
 * @program project-common
 * @description
 * @createtime 2021-02-26 15:46
 */
@Api(tags = "通知模块")
@RestController
@RequestMapping("/api/notify/v1")
@Slf4j
public class NotifyController
{
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private NotifyService notifyService;

    /**
     * 临时使用10分钟有效，方便测试
     */
    private static final long CAPTCHA_CODE_EXPIRED = 60 * 1000 * 10;

    @ApiOperation("获取图形验证码")
    @RequestMapping("/captcha")
    public void getCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
        String text = captchaProducer.createText();
        //存储
        redisTemplate.opsForValue().set(getCaptchaKey(httpServletRequest), text, CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);

        BufferedImage image = captchaProducer.createImage(text);
        try (ServletOutputStream outputStream = httpServletResponse.getOutputStream())
        {
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            httpServletResponse.addHeader("Cache-Control", "create_date-check=0, pre-check=0");
            httpServletResponse.setHeader("Pragma", "no-cache");

            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e)
        {
            log.error("获取图形验证码:{}", e.getMessage());
            log.error(e.toString());
        }

    }

    /**
     * 创建图形验证码缓存key
     *
     * @param httpServletRequest httpServletRequest
     * @return String
     * @author Lix.
     * @date 2021/2/27 14:48
     */
    private String getCaptchaKey(HttpServletRequest httpServletRequest)
    {
        String ipAddr = CommonUtil.getIpAddr(httpServletRequest);
        String userAgent = httpServletRequest.getHeader("User-Agent");
        return "user-service:captcha:" + CommonUtil.MD5(ipAddr + userAgent);
    }

    /**
     * 支持手机号、邮箱发送验证码
     * @return JsonData
     */
    @ApiOperation("发送验证码")
    @GetMapping("sendRegisterCode")
    public JsonData sendRegisterCode(@ApiParam("收信人") @RequestParam(value = "to", required = true)String to,
                                     @ApiParam("图形验证码") @RequestParam(value = "captcha", required = true)String  captcha,
                                     HttpServletRequest request){

        String key = getCaptchaKey(request);
        String cacheCaptcha = redisTemplate.opsForValue().get(key);

        if(cacheCaptcha != null && cacheCaptcha.equalsIgnoreCase(captcha)) {
            redisTemplate.delete(key);
            return notifyService.sendCode(SendCodeEnum.USER_REGISTER,to);
        }else {
            return JsonData.buildResult(BizCodeEnum.CODE_CAPTCHA);
        }
    }

}
