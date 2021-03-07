package com.chengyu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 */
@ConfigurationProperties(prefix = "aliyun.oss")
@Configuration
@Data
public class OssConfig
{
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketname;
}
