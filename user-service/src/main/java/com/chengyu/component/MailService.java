package com.chengyu.component;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-02-27 15:09
 */
public interface MailService
{
    /**
     * 发送邮件
     * @author Lix.
     * @param to 发送给谁
     * @date 2021/2/27 15:09
     */
    void sendSimpleMail(String to, String subject, String content);
}
