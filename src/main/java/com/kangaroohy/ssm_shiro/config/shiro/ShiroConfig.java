package com.kangaroohy.ssm_shiro.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kangaroo on 2019/12/9 11:54.
 * QQ: 326170945
 * Description：
 */
@Configuration
public class ShiroConfig {
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //System.out.println("执行ShiroFilterFactoryBean.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //需要登陆的接口，没有登陆就访问需要登陆的接口，则调用这个接口（非前后端分离，则调用登录界面）
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");
        //登录成功，跳转url，前后端分离的情况下，则没有这个接口的调用
        shiroFilterFactoryBean.setSuccessUrl("/");
        //已经登陆，但是访问的接口没有权限，类似403界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/unauthorized");

        //自定义退出后重定向的地址，前后端分离，用于返回退出成功信息
        LogoutFilter logout = new LogoutFilter();
        logout.setRedirectUrl("/pub/logout");

        //设置自定义filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //自定义权限filter
        filterMap.put("roleOrFilter", new CustomAuthorizationFilter());
        //自定义退出filter
        filterMap.put("logout",logout);
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截器路径，务必设置为LinkedHashMap，否则部分路径拦截时有时无（不生效），因为如果使用HashMap，无序，而LinkedHashMap，有序
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //退出过滤器，退出成功后，默认返回LoginUrl接口，即登录页，通过自定义，改到/pub/logout接口
        filterChainDefinitionMap.put("/logout", "logout");
        //匿名可访问
        filterChainDefinitionMap.put("/pub/**", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
        //登录后可以访问
        filterChainDefinitionMap.put("/user/**", "authc");
        //管理员才能访问，这是原生方法，需要同时满足才能访问
        //filterChainDefinitionMap.put("/admin/**","roles[admin,root]");
        //这个只需要满足一个即可访问，通过自定义filter实现
        filterChainDefinitionMap.put("/role/**", "roleOrFilter[admin,root]");
        //通过认证才能访问
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //如果不是前后端分离，不用设置这个
        securityManager.setSessionManager(sessionManager());
        //设置realm（推荐放到最后）
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /**
     * 自定义realm
     * @return
     */
    @Bean(name = "customRealm")
    public CustomRealm customRealm() {
        CustomRealm realm = new CustomRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        return realm;
    }

    /**
     * 密码加解密规则设置
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置散列算法，这里使用MD5
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }

    //自定义SessionManager，这个地方可以设置session的存储位置
    @Bean(name = "sessionManager")
    public SessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        //过期时间，默认30分钟超时，方法中单位是毫秒，此处15分钟过期：15 * 60 * 1000
        sessionManager.setGlobalSessionTimeout(900000);
        return sessionManager;
    }

    /**
     * 管理shiro一些Bean的生命周期，初始化和销毁
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 使注解生效，不加这个，shrio的AOP注解不生效
     * 如 Controller中 shiro的 @RequiresGust（游客可以访问） 注解
     * @return
     */
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager());
        return sourceAdvisor;
    }

    /**
     * 用来扫描上下文，寻找所有的Advisor（通知器），将符合条件的Advisor应用到切入点的Bean中
     * 需要在LifecycleBeanPostProcessor创建之后才可以创建
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        //defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
