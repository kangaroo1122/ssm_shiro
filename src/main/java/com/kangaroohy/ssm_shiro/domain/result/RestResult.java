package com.kangaroohy.ssm_shiro.domain.result;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by kangaroo on 2019/12/3 21:53.
 * QQ: 326170945
 * Description：
 */
public class RestResult<T> implements Serializable {
    private static final long serialVersionUID = 3758864789222317092L;

    @JSONField(ordinal = 1)
    private int code;
    @JSONField(ordinal = 2)
    private String msg;
    @JSONField(ordinal = 3)
    private T data;

    public RestResult<T> setCode(RestCode restCode) {
        this.code = restCode.getCode();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public RestResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RestResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
