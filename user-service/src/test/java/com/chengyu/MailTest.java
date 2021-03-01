package com.chengyu;

import com.chengyu.component.MailService;
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
        mailService.sendSimpleMail("lix@longrise.com.cn", "项目管理系统", "项目管理系统验证码【ER24】");
    }
}
