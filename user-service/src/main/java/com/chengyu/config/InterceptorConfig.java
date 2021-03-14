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

    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/api/user/*/**")
                .excludePathPatterns("/api/user/*/register", "/api/user/*/login", "/api/user/*/upload");
    }
}
