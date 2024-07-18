package qqzsh.top.addressbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.addressbook.constant.SystemConstant;
import qqzsh.top.addressbook.entity.R;
import qqzsh.top.addressbook.entity.User;
import qqzsh.top.addressbook.service.UserService;
import qqzsh.top.addressbook.util.JwtUtils;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-15 20:34
 * @Description 登录Controller
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public R login(@RequestBody User user) throws Exception {
        User u = userService.login(user);
        R r = new R();
        if (u == null) {
            return R.error("用户名或者密码错误");
        } else {
            String token = JwtUtils.createJWT(String.valueOf(u.getId()), u.getUserName(), SystemConstant.JWT_TTL);
            r.put("token", token);
        }
        return r;
    }

    /**
     * 注册
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/register")
    public R register(@RequestBody User user){
        //查重
        if (userService.findByUserName(user.getUserName()) == null){
            userService.save(user);
            return R.ok("注册成功！3s后跳转登陆页面");
        }else {
            return R.error("用户名已存在");
        }
    }
}
