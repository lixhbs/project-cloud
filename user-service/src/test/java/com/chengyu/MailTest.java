package com.chengyu;

import com.chengyu.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-02-27 15:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class MailTest
{
    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail(){
        mailService.sendSimpleMail("lix@longrise.com.cn", "迎来到小滴课堂，学习主流it技术", "哈哈，这个就是内容，https://xdclass.net");
    }
}
