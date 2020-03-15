package com.kangaroohy.ssm_shiro.config.shiro;

import com.kangaroohy.ssm_shiro.domain.entity.po.Permission;
import com.kangaroohy.ssm_shiro.domain.entity.vo.RoleVO;
import com.kangaroohy.ssm_shiro.domain.entity.vo.UserVO;
import com.kangaroohy.ssm_shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kangaroo
 * @date 2019/12/9 15:37
 * Description：
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权时调用（权限校验）
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //System.out.println("授权 AuthorizationInfo");
        //String username = (String) principalCollection.getPrimaryPrincipal();
        //获得当前对象
        UserVO user = (UserVO) principalCollection.getPrimaryPrincipal();
        UserVO info = userService.findUserAllInfoByUsername(user.getUser().getUsername());

        List<String> stringRolesList = new ArrayList<>();
        List<String> stringPermissionsList = new ArrayList<>();

        List<RoleVO> roleList = info.getRoleList();
        for (RoleVO role : roleList) {
            stringRolesList.add(role.getRole().getName());
            List<Permission> permissionList = role.getPermissionList();
            for (Permission permission : permissionList){
                if (permission != null){
                    stringPermissionsList.add(permission.getName());
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRolesList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionsList);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录时调用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("认证 AuthenticationInfo");
        //从token中获得用户信息，这个token是用户的输入信息
        String username = (String) authenticationToken.getPrincipal();
        UserVO info = userService.findUserAllInfoByUsername(username);

        //取密码
        String password = info.getUser().getPassword();
        if (password == null || "".equals(password)) {
            return null;
        }
        //设置盐值，使用账号作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //未加盐
        //return new SimpleAuthenticationInfo(username, password, this.getClass().getName());
        //加盐
        //return new SimpleAuthenticationInfo(username, password, credentialsSalt, this.getClass().getName());
        //参数一传入对象，便于全局获取当前对象信息
        return new SimpleAuthenticationInfo(info, password, credentialsSalt, this.getClass().getName());
    }
}
