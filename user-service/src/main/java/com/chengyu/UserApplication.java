package com.chengyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lix.
 * @program project-cloud
 * @createtime 2021-02-25 14:32
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.chengyu.mapper")
public class UserApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(UserApplication.class, args);
    }
}
