package com.kangaroohy.ssm_shiro.config.system;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kangaroo
 * @version 1.0.0
 * @desc TODO
 * @since 2019/12/28
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!"".equals(token.trim())) {
            return true;
        } else {
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("code", 401);
            map.put("msg", "请认证");
            JSONObject object = new JSONObject(map);
            PrintWriter out = response.getWriter();
            out.write(object.toString());
            out.flush();
            out.close();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
