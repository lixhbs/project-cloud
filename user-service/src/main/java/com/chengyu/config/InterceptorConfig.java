package com.chengyu.config;

import com.chengyu.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-14 11:15
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer
{

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoginInterceptor())
                // 拦截
                .addPathPatterns("/api/user/*/**")
                // 排除拦截
                .excludePathPatterns("/api/user/*/register",
                        "/api/user/*/login", "/api/user/*/upload");
    }
}
