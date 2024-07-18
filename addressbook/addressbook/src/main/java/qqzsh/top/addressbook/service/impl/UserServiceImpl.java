package qqzsh.top.addressbook.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqzsh.top.addressbook.entity.User;
import qqzsh.top.addressbook.mapper.UserMapper;
import qqzsh.top.addressbook.service.UserService;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-15 20:32
 * @Description 登录Service实现类
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;


    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }
}
