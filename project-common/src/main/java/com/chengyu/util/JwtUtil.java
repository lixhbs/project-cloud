package com.chengyu.util;

import com.chengyu.model.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-12 15:22
 */
@Slf4j
public class JwtUtil
{

    /**
     * token 过期时间，正常是7天，方便测试我们改为70
     */
    private static final long EXPIRE = 1000L * 60 * 60 * 24 * 7 * 10;

    /**
     * 加密的秘钥
     */
    private static final String SECRET = "b3EeWr64qnshd2RS";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "PROJECT_CLOUD:";

    /**
     * subject
     */
    private static final String SUBJECT = "project-cloud";


    /**
     * 根据用户信息，生成令牌
     *
     * @param loginUser loginUser
     * @return String
     */
    public static String geneJsonWebToken(LoginUser loginUser)
    {

        if (loginUser == null)
        {
            throw new NullPointerException("loginUser对象为空");
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                //payload
                .claim("head_img", loginUser.getHeadImg())
                .claim("id", loginUser.getId())
                .claim("name", loginUser.getName())
                .claim("mail", loginUser.getMail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();

        token = TOKEN_PREFIX + token;
        return token;
    }


    /**
     * 校验token的方法
     *
     * @param token token
     * @return Claims
     */
    public static Claims checkJwt(String token)
    {
        try
        {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
        } catch (Exception e)
        {
            log.info("jwt token解密失败");
            return null;
        }
    }



}
