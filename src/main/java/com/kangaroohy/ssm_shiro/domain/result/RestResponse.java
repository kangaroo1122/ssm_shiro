package com.kangaroohy.ssm_shiro.domain.result;

/**
 * Created by kangaroo on 2019/12/3 21:52.
 * QQ: 326170945
 * Description：
 */
public class RestResponse {
    public final static String RESP_SUCCESS = "SUCCESS";

    public static <T> RestResult<T> responseOK() {
        return new RestResult<T>().setCode(RestCode.SUCCESS).setMsg(RESP_SUCCESS);
    }

    public static <T> RestResult<T> responseOK(T data) {
        return new RestResult<T>().setCode(RestCode.SUCCESS).setMsg(RESP_SUCCESS).setData(data);
    }

    public static <T> RestResult<T> responseError(String message) {
        return new RestResult<T>().setCode(RestCode.UNKNOWN_ERROR).setMsg(message);
    }

    public static <T> RestResult<T> makeRsp(RestCode restCode) {
        return new RestResult<T>().setCode(restCode).setMsg(restCode.getMessage());
    }

    public static <T> RestResult<T> makeRsp(RestCode restCode, String msg) {
        return new RestResult<T>().setCode(restCode).setMsg(msg);
    }

    public static <T> RestResult<T> makeRsp(RestCode restCode, String msg, T data) {
        return new RestResult<T>().setCode(restCode).setMsg(msg).setData(data);
    }
}
