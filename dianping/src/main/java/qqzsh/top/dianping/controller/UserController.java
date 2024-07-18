package qqzsh.top.dianping.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.CommonRes;
import qqzsh.top.dianping.common.CommonUtil;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.pojo.User;
import qqzsh.top.dianping.request.LoginReq;
import qqzsh.top.dianping.request.RegisterReq;
import qqzsh.top.dianping.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-12-11 14:47
 * @description 用户相关
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    private UserService userService;
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/test")
    public String get() {
        userService.save(User.builder().createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now())
        .telphone("15537364889").password("123456").nickName("zsh").gender(0).build());
        return "test";
    }

    @GetMapping("/get")
    public CommonRes getUser(@RequestParam(name = "id") String id) throws BusinessException {
        User user = userService.getById(Long.valueOf(id));
        if(user == null){
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        }else{
            return CommonRes.create(user);
        }
    }

    /**
     * 注册
     * @param registerReq
     * @param bindingResult
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/register")
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //校验参数有错误直接返回
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        //封装注册用户对象
        User registerUser = User.builder()
                .telphone(registerReq.getTelphone())
                .password(registerReq.getPassword())
                .nickName(registerReq.getNickName())
                .gender(registerReq.getGender())
                .role("user")
                .build();
        return CommonRes.create(userService.register(registerUser));
    }

    /**
     * 登录
     * @param loginReq
     * @param bindingResult
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/login")
    public CommonRes login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //校验参数有错误直接返回
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,CommonUtil.processErrorString(bindingResult));
        }
        //登录
        User user = userService.login(loginReq.getTelphone(),loginReq.getPassword());
        //登录成功，用户信息放入session
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,user);
        return CommonRes.create(user);
    }

    /**
     * 注销
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/logout")
    public CommonRes logout() {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    /**
     * 获取当前用户信息
     * @return
     */
    @PostMapping("/getcurrentuser")
    public CommonRes getCurrentUser(){
        return CommonRes.create(httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION));
    }

}
