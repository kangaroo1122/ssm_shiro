package com.kangaroohy.ssm_shiro.domain.result;

/**
 * Created by kangaroo on 2019/12/3 21:51.
 * QQ: 326170945
 * Description：
 */
public enum RestCode {
    //运行时错误
    UNKNOWN_ERROR(-1, "Unknown Error! Contact the administrator if this problem continues."),
    //操作成功
    SUCCESS(200, "Success!"),
    //请求参数错误、不合法
    PARAM_ERROR(400, "Illegal Parameter!"),
    //需要登录才能访问
    NEED_LOGIN_ERROR(401, "Please login and visit again!"),
    //没有访问权限，禁止访问
    AUTHORIZATION_ERROR(403, "You are forbidden to view it!"),
    //登录错误，用户名或者密码错误
    INCORRECT_ERROR(4010, "Incorrect username or password!"),
    //数据库链接异常
    DATABASE_ERROR(4011, "Database Exception!");

    private Integer code;
    private String message;

    RestCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
