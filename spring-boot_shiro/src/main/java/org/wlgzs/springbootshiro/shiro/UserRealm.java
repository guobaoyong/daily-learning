package org.wlgzs.springbootshiro.shiro;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.wlgzs.springbootshiro.domain.User;
import org.wlgzs.springbootshiro.service.UserService;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-11 9:26
 * @Describe 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
        //info.addStringPermission("user:add");
        //获取当前登陆用户
        Subject subject = SecurityUtils.getSubject();
        User u = (User) subject.getPrincipal();
        User byId = userService.findById(u.getId());
        info.addStringPermission(byId.getPerms());
        return info;
    }

    //注入业务
    @Autowired
    private UserService userService;

    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User byName = userService.findByName(usernamePasswordToken.getUsername());

        if (byName == null){
            //用户名不存在
            //返回null，shiro底层会抛出UnknownAccountException
            return null;
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(byName,byName.getPassword(),"");
    }
}
