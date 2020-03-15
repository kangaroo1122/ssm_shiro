package com.kangaroohy.ssm_shiro.controller;

import com.kangaroohy.ssm_shiro.domain.entity.vo.UserVO;
import com.kangaroohy.ssm_shiro.domain.result.RestResponse;
import com.kangaroohy.ssm_shiro.domain.result.RestResult;
import com.kangaroohy.ssm_shiro.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public RestResult<List<UserVO>> selectUsersWithRoles(){
        List<UserVO> list = userService.findUsersWithRoles();
        System.out.println(list);
        for (UserVO user : list){
            user.getUser().setPassword(null);
        }
        return RestResponse.responseOK(list);
    }

    @GetMapping("/info")
    @RequiresRoles(value = {"admin", "root"}, logical = Logical.OR)
    public UserVO findUserAllInfoByUsername(@RequestParam String username){
        UserVO info = userService.findUserAllInfoByUsername(username);
        info.getUser().setPassword(null);
        return info;
    }
}
