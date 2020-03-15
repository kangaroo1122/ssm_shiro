package com.kangaroohy.ssm_shiro.config.system;

import com.kangaroohy.ssm_shiro.domain.result.RestCode;
import com.kangaroohy.ssm_shiro.domain.result.RestResponse;
import com.kangaroohy.ssm_shiro.domain.result.RestResult;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kangaroo on 2019/12/11 0:15.
 * QQ: 326170945
 * Description：统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public RestResult<String> runtimeExceptionHandler(HttpServletRequest req, Exception e){
        System.out.println(e.getMessage());
        return RestResponse.makeRsp(RestCode.UNKNOWN_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public RestResult<String> unauthorizedExceptionHandler(HttpServletRequest req, Exception e){
        System.out.println(e.getMessage());
        return RestResponse.makeRsp(RestCode.AUTHORIZATION_ERROR);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public RestResult<String> unauthenticatedExceptionHandler(HttpServletRequest req, Exception e){
        System.out.println(e.getMessage());
        return RestResponse.makeRsp(RestCode.AUTHORIZATION_ERROR, "抱歉，你没有此权限");
    }
}
