package com.chengyu.util;

import com.chengyu.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lix.
 * @title
 * @program project-common
 * @description
 * @createtime 2021-02-25 17:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData
{

    /**
     * 状态码 0 表示成功，1表示处理中，-1表示失败
     */

    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;


    /**
     * 成功，不传入数据
     * @return JsonData
     */
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    /**
     *  成功，传入数据
     * @param data data
     * @return JsonData
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    /**
     * 失败，传入描述信息
     * @param msg msg
     * @return JsonData
     */
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    /**
     * 失败，传入描述信息和代码
     * @param msg msg
     * @return JsonData
     */
    public static JsonData buildError(String msg, int code) {
        return new JsonData(code, null, msg);
    }


    /**
     * 自定义状态码和错误信息
     * @param code
     * @param msg
     * @return
     */
    public static JsonData buildCodeAndMsg(int code, String msg) {
        return new JsonData(code, null, msg);
    }

    /**
     * 传入枚举，返回信息
     * @param codeEnum
     * @return
     */
    public static JsonData buildResult(BizCodeEnum codeEnum){
        return JsonData.buildCodeAndMsg(codeEnum.getCode(),codeEnum.getMessage());
    }
}
