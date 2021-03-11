package com.chengyu.component.impl;

import com.chengyu.component.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


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
        MimeMessage message=mailSender.createMimeMessage();
        //true表示需要创建一个multipart message
        try
        {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            //配置邮箱发送人
            helper.setFrom(from);
            //邮件的收件人 因为一直发送失败，所以将自己也添加到收件人，这样就好了
            helper.setTo(to);
            //邮件的主题
            helper.setSubject(subject);
            helper.addCc(from);
            //邮件的内容
            helper.setText(content, true);
            mailSender.send(message);
            log.info("邮件发送成功:{}",message.toString());
        } catch (MessagingException e)
        {
            log.error("邮件发送失败:{}",message.toString());
            e.printStackTrace();

        }
    }
}
