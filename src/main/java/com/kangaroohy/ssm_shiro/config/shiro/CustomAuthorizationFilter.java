package com.kangaroohy.ssm_shiro.config.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

/**
 *
 * @author kangaroo
 * @date 2019/12/9 23:53
 * Description：自定义filter，满足其中一个权限，则返回true，而不是所有权限必须同时满足
 */
public class CustomAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = this.getSubject(servletRequest, servletResponse);
        //获取访问当前路径所需要的权限集合
        String[] rolesArray = (String[]) ((String[]) o);
        if (rolesArray != null && rolesArray.length != 0) {
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            for (String role : roles) {
                if (subject.hasRole(role)) {
                    return true;
                }
            }
            return false;
        } else {//没有角色限制，直接返回true
            return true;
        }
    }
}
