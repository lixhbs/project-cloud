package com.chengyu.exception;

import com.chengyu.enums.BizCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lix.
 * @title
 * @program project-common
 * @description
 * @createtime 2021-02-25 18:01
 */
@Data
public class BizException extends RuntimeException
{
    private Integer code;
    private String msg;

    public BizException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(BizCodeEnum bizCodeEnum){
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }
}
