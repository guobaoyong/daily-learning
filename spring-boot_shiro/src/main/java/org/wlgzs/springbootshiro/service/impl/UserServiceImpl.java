package org.wlgzs.springbootshiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.springbootshiro.dao.UserDao;
import org.wlgzs.springbootshiro.domain.User;
import org.wlgzs.springbootshiro.service.UserService;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-11 11:53
 * @Describe
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    //注入dao接口
    @Autowired
    private UserDao userDao;

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
