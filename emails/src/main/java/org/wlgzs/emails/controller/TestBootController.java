package org.wlgzs.emails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.emails.model.User;
import org.wlgzs.emails.service.UserService;
import org.wlgzs.emails.util.EmailUtils;
import org.wlgzs.emails.util.GenerateLinkUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-02 17:03
 * @Describe
 */
@RestController
public class TestBootController {
    @Autowired
    private UserService userService;

    //跳转到主页
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    //跳转到注册页面
    @RequestMapping("registerUI")
    public ModelAndView registerUI(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        Map<String, String> param = new HashMap<String,String>();
        param.put("userName",null);
        param.put("email",null);
        modelAndView.addObject("param",param);
        Map<String, String> errors = new HashMap<String,String>();
        errors.put("userName",null);
        errors.put("password",null);
        errors.put("password2",null);
        errors.put("email",null);
        modelAndView.addObject("errors",errors);
        return modelAndView;
    }

    //跳转到登陆页面
    @RequestMapping("loginUI")
    public ModelAndView loginUI(){
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> param = new HashMap<String,String>();
        param.put("userName",null);
        modelAndView.addObject("param",param);
        Map<String, String> errors = new HashMap<String,String>();
        errors.put("userName",null);
        errors.put("password",null);
        errors.put("loginError",null);
        modelAndView.addObject("errors",errors);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    //跳转到修改个人信息页面
    @RequestMapping("updateUI")
    public ModelAndView updateUI(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.selectByPrimaryKey(id);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("update");
        Map<String, String> errors = new HashMap<String,String>();
        errors.put("email",null);
        modelAndView.addObject("errors",errors);
        return modelAndView;
    }

    //跳转到忘记密码页面
    @RequestMapping("forgotPwdUI")
    public ModelAndView forgotPwdUI(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg",null);
        modelAndView.setViewName("forgotPwd");
        return modelAndView;
    }

    //跳转到忘记密码页面
    @RequestMapping("resetPasswordUI")
    public ModelAndView resetPasswordUI(String userName,String checkCode){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.selectByUserName(userName);
        if (GenerateLinkUtils.verifyCheckcode(user,checkCode,1)){
            user.setRandomcode(null);
            userService.updateByPrimaryKey(user);
            modelAndView.addObject("Msg","验证成功");
            modelAndView.addObject("user",user);
            Map<String, String> errors = new HashMap<String,String>();
            errors.put("passwordError",null);
            errors.put("newPassword",null);
            errors.put("newPassword2",null);
            modelAndView.addObject("errors",errors);
            modelAndView.setViewName("resetPassword");
            return modelAndView;
        }else {
            modelAndView.addObject("Msg","验证失败");
            modelAndView.setViewName("resetPassword");
            return modelAndView;
        }
    }

    //注册
    @PostMapping("register")
    public ModelAndView register(User user,String password2){
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> errors = new HashMap<String,String>();
        int a = 0;
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            errors.put("userName", "用户名不能为空!");
        } else if (user.getUsername() != null && userService.selectByUserName(user.getUsername()) != null) {
            errors.put("userName", "该用户已注册!");
        }else {
            a++;
            errors.put("userName", null);
        }

        if (user.getPassword() == null || "".equals(user.getPassword())) {
            errors.put("password","密码不能为空!");
        } else if (user.getPassword() != null && user.getPassword().length() < 3) {
            errors.put("password","密码长度不能低于3位!");
        }else {
            a++;
            errors.put("password", null);
        }

        if (password2 == null || "".equals(password2)) {
            errors.put("password2", "确认密码不能为空!");
        } else if (password2 != null && !password2.equals(user.getPassword())) {
            errors.put("password2", "两次输入的密码不一致!");
        }else {
            a++;
            errors.put("password2", null);
        }

        if (user.getEmail() == null || "".equals(user.getEmail())) {
            errors.put("email", "email不能为空!");

        }else if (user.getEmail() != null && userService.selectByEmail(user.getEmail()) != null){
            errors.put("email", "email已被注册");
        }else if (user.getEmail() != null && !user.getEmail().matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
            errors.put("email", "email格式不正确!");
        }else {
            a++;
            errors.put("email", null);
        }

        if (a != 4) {
            modelAndView.setViewName("register");
            modelAndView.addObject("errors",errors);
            Map<String, String> param = new HashMap<String,String>();
            param.put("userName",user.getUsername());
            param.put("email",user.getEmail());
            modelAndView.addObject("param",param);
            return modelAndView;
        }else {
            user.setActivated(false);
            userService.insert(user);
            // 注册成功后,发送帐户激活链接
            EmailUtils.sendAccountActivateEmail(user);
            modelAndView.setViewName("registerSuccess");
            modelAndView.addObject("user",user);
            return modelAndView;
        }
    }

    //邮箱激活
    @GetMapping("activate")
    public ModelAndView ActivateAccount(String checkCode,Integer id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.selectByPrimaryKey(id);
        if (GenerateLinkUtils.verifyCheckcode(user,checkCode,0)){
            user.setActivated(true);
            user.setRandomcode(null);
            userService.updateByPrimaryKey(user);
        }
        modelAndView.addObject("user",user);
        modelAndView.setViewName("accountActivateSuccess");
        return modelAndView;
    }

    //登陆
    @PostMapping("login")
    public ModelAndView login(User user){
        System.out.println(user);
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> param = new HashMap<String,String>();
        Map<String, String> errors = new HashMap<String,String>();
        int a  = 0;
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            errors.put("userName", "用户名不能为空!");
        }else {
            errors.put("userName",null);
            a++;
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            errors.put("password","密码不能为空!");
        } else if (user.getPassword() != null && user.getPassword().length() < 3) {
            errors.put("password","密码长度不能低于3位!");
        }else{
            errors.put("password",null);
            a++;
        }
        User user1 = userService.selectByUserName(user.getUsername());
        if (user1 == null || !user1.getPassword().equals(user.getPassword())) {
            errors.put("loginError", "用户名或密码不正确！");
        }
        if (a != 2){
            modelAndView.setViewName("login");
            param.put("userName",user.getUsername());
            param.put("password",user.getPassword());
            modelAndView.addObject("param",param);
            modelAndView.addObject("errors",errors);
            return modelAndView;
        }else {
            modelAndView.setViewName("loginSuccess");
            modelAndView.addObject("user",user1);
            return modelAndView;
        }
    }

    //再次申请激活
    @RequestMapping("applyActivateLink")
    public void applyActivateLink(Integer id){
        User user = userService.selectByPrimaryKey(id);
        EmailUtils.sendAccountActivateEmail(user);
    }

    //修改个人信息
    @PostMapping("update")
    public ModelAndView update(String email,Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> errors = new HashMap<String,String>();
        if (email == null || "".equals(email)) {
            errors.put("email", "email不能为空!");
        } else if (email != null && !email.matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
            errors.put("email", "email格式不正确!");
        }
        final User user = userService.selectByPrimaryKey(id);
        if (!errors.isEmpty()){
            modelAndView.addObject("errors",errors);
            modelAndView.setViewName("update");
            modelAndView.addObject("user",user);
            return modelAndView;
        }else {
            user.setEmail(email);
            user.setActivated(false);
            userService.updateByPrimaryKey(user);
            EmailUtils.sendAccountActivateEmail(user);
            modelAndView.addObject("user",user);
            modelAndView.setViewName("updateSuccess");
            return modelAndView;
        }
    }

    //忘记密码
    @PostMapping("forgotPwd")
    public ModelAndView forgotPwd(String userNameOrEmail){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.selectByUserNameOrEmail(userNameOrEmail);
        if (user == null){
            modelAndView.addObject("errorMsg",userNameOrEmail+"不存在!");
            modelAndView.setViewName("forgotPwd");
            return modelAndView;
        }else {
            EmailUtils.sendResetPasswordEmail(user);
            modelAndView.setViewName("forgotPwdSuccess");
            modelAndView.addObject("sendMailMsg","您的申请已提交成功，请查看您的"+user.getEmail()+"邮箱。");
            return modelAndView;
        }
    }

    //修改密码
    @PostMapping("resetPassword")
    public ModelAndView resetPassword(Integer id,User user,String password2){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Msg","验证成功");
        Map<String, String> errors = new HashMap<String,String>();
        int a = 0;
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            errors.put("newPassword", "新密码不能为空！");
        }else if (user.getPassword() != null && user.getPassword().length() < 3) {
            errors.put("newPassword","密码长度不能低于3位!");
        }else {
            errors.put("newPassword",null);
            a++;
        }

        if (password2 == null || "".equals(password2)) {
            errors.put("newPassword2", "确认新密码不能为空！");
        }else if (password2 != null && password2.length() < 3) {
            errors.put("newPassword2","密码长度不能低于3位!");
        }else {
            errors.put("newPassword2", null);
            a++;
        }

        if (!user.getPassword().equals(password2)) {
            errors.put("passwordError", "两次输入的密码不一致！");
        }else {
            errors.put("passwordError", null);
            a++;
        }

        if (a != 3) {
            modelAndView.addObject("errors",errors);
            modelAndView.setViewName("resetPassword");
            return modelAndView;
        }else {
            User user1 = userService.selectByPrimaryKey(id);
            user1.setPassword(user.getPassword());
            userService.updateByPrimaryKey(user1);
            modelAndView.setViewName("resetPasswordSuccess");
            return modelAndView;
        }
    }


}
