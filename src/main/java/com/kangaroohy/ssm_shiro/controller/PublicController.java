package com.kangaroohy.ssm_shiro.controller;

import com.kangaroohy.ssm_shiro.domain.entity.vo.UserVO;
import com.kangaroohy.ssm_shiro.domain.result.RestCode;
import com.kangaroohy.ssm_shiro.domain.result.RestResponse;
import com.kangaroohy.ssm_shiro.domain.result.RestResult;
import com.kangaroohy.ssm_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author kangaroo
 * @date 2019/12/9 21:31
 * Description：
 */
@RestController
@RequestMapping("/pub")
public class PublicController {
    @Autowired
    UserService userService;

    @GetMapping("/need_login")
    public RestResult<String> needLogin(){
        return RestResponse.makeRsp(RestCode.NEED_LOGIN_ERROR);
    }

    @GetMapping("/unauthorized")
    public RestResult<String> unauthorized(){
        return RestResponse.makeRsp(RestCode.AUTHORIZATION_ERROR);
    }

    @PostMapping("/login")
    public RestResult<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> info = new LinkedHashMap<>();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            info.put("status","登录成功");
            info.put("token",subject.getSession().getId());
            UserVO infoByUsername = userService.findUserAllInfoByUsername(username);
            infoByUsername.getUser().setPassword(null);
            info.put("info", infoByUsername);
            return RestResponse.responseOK(info);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.makeRsp(RestCode.INCORRECT_ERROR, "用户名或密码错误", null);
        }
    }

    @GetMapping("/logout")
    public RestResult<String> logout(){
        return RestResponse.responseOK();
    }
}
