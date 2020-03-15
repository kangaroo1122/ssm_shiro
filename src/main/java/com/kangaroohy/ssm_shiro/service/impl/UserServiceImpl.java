package com.kangaroohy.ssm_shiro.service.impl;

import com.kangaroohy.ssm_shiro.domain.entity.po.Permission;
import com.kangaroohy.ssm_shiro.domain.entity.po.Role;
import com.kangaroohy.ssm_shiro.domain.entity.vo.RoleVO;
import com.kangaroohy.ssm_shiro.domain.entity.vo.UserVO;
import com.kangaroohy.ssm_shiro.mapper.PermissionMapper;
import com.kangaroohy.ssm_shiro.mapper.RoleMapper;
import com.kangaroohy.ssm_shiro.mapper.UserMapper;
import com.kangaroohy.ssm_shiro.domain.entity.po.User;
import com.kangaroohy.ssm_shiro.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserMapper userMapper;

    private RoleMapper roleMapper;

    private PermissionMapper permissionMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<UserVO> findUsersWithRoles() {
        List<User> userList = userMapper.selectList(null);
        List<UserVO> userVOList = new ArrayList<>();
        for (User user : userList){
            user.setPassword("");
            List<Role> roleList = roleMapper.selectRolesByUserId(user.getId());
            List<RoleVO> roleVOList = new ArrayList<>();
            for (Role role : roleList){
                roleVOList.add(RoleVO.builder().role(role).build());
            }
            userVOList.add(UserVO.builder().user(user).roleList(roleVOList).build());
        }
        return userVOList;
    }

    @Override
    public UserVO findUserAllInfoByUsername(String username) {
        //这里可以加缓存，先从缓存中查询，没有再从数据库中查询，然后存入缓存，再返回数据
        User user = userMapper.selectByUsername(username);
        List<Role> roleList = roleMapper.selectRolesByUserId(user.getId());

        List<RoleVO> roleVOS = new ArrayList<>();
        for (Role role : roleList){
            RoleVO roleVO = new RoleVO();
            roleVO.setRole(role);
            List<Permission> permissions = permissionMapper.selectPermissionsByRoleId(role.getId());
            roleVO.setPermissionList(permissions);
            roleVOS.add(roleVO);
        }
        return UserVO.builder()
                .user(user)
                .roleList(roleVOS)
                .build();
    }

}
