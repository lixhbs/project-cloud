package com.chengyu.interceptor;

import com.chengyu.enums.BizCodeEnum;
import com.chengyu.model.LoginUser;
import com.chengyu.util.CommonUtil;
import com.chengyu.util.JsonData;
import com.chengyu.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-12 22:10
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor
{

    private static final ThreadLocal<LoginUser> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        THREAD_LOCAL.remove();
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token))
        {
            token = request.getParameter("token");
        }

        if (StringUtils.isNotBlank(token))
        {
            Claims claims = JwtUtil.checkJwt(token);
            if (claims == null)
            {
                CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
                return false;
            }
            String id = (String) claims.get("id");
            String headImg = (String) claims.get("head_img");
            String name = (String) claims.get("name");
            String mail = (String) claims.get("mail");

            LoginUser loginUser = LoginUser.builder().id(id).name(name).headImg(headImg).mail(mail).build();
            THREAD_LOCAL.set(loginUser);
            return true;

        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }
}
