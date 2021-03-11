package com.chengyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengyu.enums.BizCodeEnum;
import com.chengyu.enums.SendCodeEnum;
import com.chengyu.mapper.UserMapper;
import com.chengyu.model.UserDO;
import com.chengyu.request.UserLoginRequest;
import com.chengyu.request.UserRegisterRequest;
import com.chengyu.service.NotifyService;
import com.chengyu.service.UserService;
import com.chengyu.util.CommonUtil;
import com.chengyu.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lix.
 * @title
 * @program project-common
 * @description
 * @createtime 2021-02-25 16:50
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotifyService notifyService;

    @Override
    public UserDO detail(String id)
    {
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq("id", id));
    }

    @Override
    public JsonData register(UserRegisterRequest registerRequest)
    {
        // 判断验证码
        boolean checkCode = false;
        if(StringUtils.isNotBlank(registerRequest.getMail())){
            checkCode = notifyService.checkCode(SendCodeEnum.USER_REGISTER, registerRequest.getMail(), registerRequest.getCode());
        }
        if(!checkCode){
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(registerRequest, userDO);
        userDO.setCreatedTime(new Date());

        // 设置密码
        userDO.setSecret("$1$" + CommonUtil.getStringNumRandom(8));

        //密码 + 加盐处理
        String cryptPwd = Md5Crypt.md5Crypt(registerRequest.getPwd().getBytes(), userDO.getSecret());
        userDO.setPwd(cryptPwd);
        int rows = userMapper.insert(userDO);
        log.info("rows: {}, 注册成功: {}", rows, userDO.toString());

        // @TODO 判断账号是否唯一

        return JsonData.buildSuccess();
    }

    @Override
    public JsonData login(UserLoginRequest loginRequest)
    {
        List<UserDO> list = userMapper.selectList(
                new QueryWrapper<UserDO>().eq("mail", loginRequest.getMail()));

        if (list != null && list.size() == 1) {
            UserDO userDO = list.get(0);
            String cryptPwd = Md5Crypt.md5Crypt(loginRequest.getPwd().getBytes(), userDO.getSecret());
            if (cryptPwd.equals(userDO.getPwd())) {
                //生成token令牌
                log.info("用户{}，登录成功", userDO.getName());
                return JsonData.buildSuccess();
            }
            //密码错误
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_PWD_ERROR);
        } else {
            //未注册
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_UNREGISTER);
        }
    }
}
