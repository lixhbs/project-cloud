package com.chengyu.exception;

import com.chengyu.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lix.
 * @title
 * @program project-common
 * @description
 * @createtime 2021-02-26 09:31
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandle
{
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e)
    {
        if (e instanceof BizException)
        {
            BizException bizException = (BizException) e;
            log.error("[业务异常]{}", e);
            return JsonData.buildError(bizException.getMsg(), bizException.getCode());
        } else
        {
            log.error("[系统异常]{}", e);
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}
