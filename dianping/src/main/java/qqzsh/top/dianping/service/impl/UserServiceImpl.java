package qqzsh.top.dianping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.mapper.UserMapper;
import qqzsh.top.dianping.pojo.User;
import qqzsh.top.dianping.service.UserService;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-12-11 15:21
 * @description 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @Transactional
    public User register(User registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        // 密码使用MD5加密
        registerUser.setPassword(encodeByMd5(registerUser.getPassword()));
        // 设置当前时间为创建、更新时间
        registerUser.setCreatedAt(LocalDateTime.now());
        registerUser.setUpdatedAt(LocalDateTime.now());
        try{
            // 保存
            super.save(registerUser);
        }catch (DuplicateKeyException ex){
            // 手机号已存在
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }
        // 返回当前用户信息
        return registerUser;
    }

    @Override
    public User login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        //根据手机号和密码查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telphone",telphone).eq("password",encodeByMd5(password));
        User user = baseMapper.selectOne(queryWrapper);
        if(user == null){
            //手机号或密码错误
            throw new BusinessException(EmBusinessError.LOGIN_FAIL);
        }
        //验证通过
        return user;
    }

    /**
     * 加密密码用
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确认计算方法MD5
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));

    }
}
