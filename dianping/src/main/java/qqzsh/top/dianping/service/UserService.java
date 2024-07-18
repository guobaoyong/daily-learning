package qqzsh.top.dianping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.pojo.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-12-11 14:48
 * @description 用户接口
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册接口
     * @param registerUser
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    User register(User registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 用户登录
     * @param telphone
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws BusinessException
     */
    User login(String telphone,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;
}
