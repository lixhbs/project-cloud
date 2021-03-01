package com.chengyu.service.Impl;

import com.chengyu.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-02-27 15:09
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService
{
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 配置文件中的发送邮箱
     */
    @Value("${spring.mail.from}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content)
    {
        //创建一个邮箱消息对象
        SimpleMailMessage message = new SimpleMailMessage();
        //配置邮箱发送人
        message.setFrom(from);
        //邮件的收件人 因为一直发送失败，所以将自己也添加到收件人，这样就好了
        message.setTo(to,from);
        //邮件的主题
        message.setSubject(subject);
        //邮件的内容
        message.setText(content);
        mailSender.send(message);
        log.info("邮件发送成功:{}",message.toString());

    }
}
