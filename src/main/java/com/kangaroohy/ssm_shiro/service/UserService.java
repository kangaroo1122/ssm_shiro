package com.kangaroohy.ssm_shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kangaroohy.ssm_shiro.domain.entity.po.User;
import com.kangaroohy.ssm_shiro.domain.entity.vo.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
public interface UserService extends IService<User> {

    //登录时调用此方法，获得当前用户的所有信息
    UserVO findUserAllInfoByUsername(String username);

    List<UserVO> findUsersWithRoles();
}
