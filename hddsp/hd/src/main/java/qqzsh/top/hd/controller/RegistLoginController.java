package qqzsh.top.hd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.hd.pojo.User;
import qqzsh.top.hd.pojo.vo.UserVO;
import qqzsh.top.hd.service.UserService;
import qqzsh.top.hd.utils.JSONResult;
import qqzsh.top.hd.utils.MD5Utils;

import java.util.UUID;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:04
 * @description
 */
@RestController
@Api(value = "用户注册和登录的接口",tags = {"注册和登录的controller"})
public class RegistLoginController extends BasicController{

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }

    @ApiOperation(value = "用户注册",notes = "用户注册的接口")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody User user) throws Exception {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return JSONResult.errorMsg("用户名或密码不能为空！");
        }
        if (userService.findByUserName(user.getUsername()) == null){
            user.setNickname(user.getUsername());
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setFans_counts(0);
            user.setFollow_counts(0);
            user.setReceive_like_counts(0);
            userService.insert(user);
        }else {
            return JSONResult.errorMsg("用户名已存在！");
        }
        user.setPassword("");
        UserVO userVO = setUserRedisSessionToken(user);
        return JSONResult.ok(userVO);
    }

    public UserVO setUserRedisSessionToken(User user){
        String uniqueToken = UUID.randomUUID().toString();
        redisOperator.set(USER_REDIS_SESSION+":"+user.getId(),
                uniqueToken,60*15);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        userVO.setUserToken(uniqueToken);
        return userVO;
    }

    @ApiOperation(value = "用户登录",notes = "用户登录接口")
    @PostMapping("/login")
    public JSONResult login(@RequestBody User user) throws Exception {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return JSONResult.errorMsg("用户名或密码不能为空！");
        }
        User currentUser = userService.findByUserName(user.getUsername());
        if (currentUser == null){
            return JSONResult.errorMsg("用户不存在！");
        }else if (currentUser.getPassword().equals(MD5Utils.getMD5Str(user.getPassword()))){
            currentUser.setPassword("");
            UserVO userVO = setUserRedisSessionToken(currentUser);
            return JSONResult.ok(userVO);
        }else {
            return JSONResult.errorMsg("密码错误！");
        }
    }

    @ApiOperation(value = "用户注销",notes = "用户注销接口")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,
            dataType = "int",paramType = "query")
    @GetMapping("/logout")
    public JSONResult login(Integer userId) {
        redisOperator.del(USER_REDIS_SESSION+":"+userId);
        return JSONResult.ok();
    }

}
