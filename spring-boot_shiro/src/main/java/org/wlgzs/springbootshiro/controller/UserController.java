package org.wlgzs.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.springbootshiro.service.UserService;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-11 9:46
 * @Describe
 */
@Controller
public class UserController {

    //进入测试主页
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "/login";
    }

    @RequestMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }

    @RequestMapping("/login")
    public String login(String name, String password, Model model){
        //使用shiro编写认证操作
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,password);
        //3.执行登陆方法
        try {
            subject.login(usernamePasswordToken);
            //登陆成功
            return "redirect:/test";
        }catch (UnknownAccountException e){
            //用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
}
