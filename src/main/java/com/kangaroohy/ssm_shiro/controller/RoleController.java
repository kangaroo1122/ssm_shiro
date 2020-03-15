package com.kangaroohy.ssm_shiro.controller;

import com.kangaroohy.ssm_shiro.domain.result.RestResponse;
import com.kangaroohy.ssm_shiro.domain.result.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/get")
    public RestResult<String> getAA(){
        return RestResponse.responseOK("role get");
    }
}
