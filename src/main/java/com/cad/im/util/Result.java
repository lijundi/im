package com.cad.im.util;

import lombok.Data;

import java.io.Serializable;


@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 7988823970938566753L;

    private Integer code;

    private String msg;

    private Object data;

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    //成功 不返回数据直接返回成功信息
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    //成功 并且加上返回数据
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    //成功 自定义成功返回状态 加上数据
    public static Result success(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    // 单返回失败的状态码
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    // 返回失败的状态码 及 数据
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}
