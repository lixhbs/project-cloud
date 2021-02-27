package com.chengyu.controller;

import com.chengyu.util.JsonData;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lix.
 * @title
 * @program project-common
 * @description
 * @createtime 2021-02-26 15:46
 */
@RestController
@RequestMapping("/api/notify/v1")
@Slf4j
public class NotifyController
{
    @Autowired
    private Producer captchaProducer;

    @RequestMapping("/captcha")
    public void getCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
        String text = captchaProducer.createText();

        log.info("图形验证码:{}", text);
        BufferedImage image = captchaProducer.createImage(text);
        try (ServletOutputStream outputStream = httpServletResponse.getOutputStream())
        {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e)
        {
            log.error("获取图形验证码:{}", e.getMessage());
            log.error(e.toString());
        }

    }

}
