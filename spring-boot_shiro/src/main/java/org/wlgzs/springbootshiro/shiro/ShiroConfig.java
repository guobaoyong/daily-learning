package org.wlgzs.springbootshiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-11 9:22
 * @Describe shiro的配置类
 */
@Configuration
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro内置过滤器
        /**
         * shiro常用过滤器，可以实现权限相关的拦截器
         *  anon:无需认证(登陆)可以访问
         *  authc:必须认证(登陆)才可以访问
         *  user:如果使用rememberMe的功能可以直接访问
         *  perms:该资源必须得到资源权限才可以访问
         *  role:该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/
        //通配方式
        filterMap.put("/test","anon");
        filterMap.put("/login","anon");
        //授权过滤器
        //注意:当前授权拦截之后，shiro会自动跳转到未授权的页面
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");
        filterMap.put("/*","authc");

        //修改跳转页面地址
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未授权页面地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //创建DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //创建Realm
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    //配置ShiroDialect，用于thymeleaf和shiro标签配合使用
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
