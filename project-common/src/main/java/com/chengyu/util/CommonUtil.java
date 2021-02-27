package com.chengyu.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-02-27 14:11
 */
@Slf4j
public class CommonUtil
{

    /**
     * 获取ip
     *
     * @param request request
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request)
    {
        String ipAddress = null;
        try
        {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
            {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
            {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
            {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress))
                {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try
                    {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e)
                    {
                        e.printStackTrace();
                    }
                    assert inet != null;
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15)
            {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0)
                {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e)
        {
            ipAddress = "";
        }
        return ipAddress;
    }

    public static String MD5(String data)
    {
        try
        {
            java.security.MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte item : array)
            {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString().toUpperCase();
        } catch (Exception e)
        {
            log.error(e.toString());
        }
        return null;

    }
}
