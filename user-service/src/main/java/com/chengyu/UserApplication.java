package com.chengyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lix.
 * @program project-cloud
 * @createtime 2021-02-25 14:32
 */
@SpringBootApplication
@MapperScan("com.chengyu.mapper")
public class UserApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(UserApplication.class, args);
    }
}
