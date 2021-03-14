package com.chengyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-14 14:59
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.chengyu.mapper")
public class ProjectApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ProjectApplication.class, args);
    }
}
