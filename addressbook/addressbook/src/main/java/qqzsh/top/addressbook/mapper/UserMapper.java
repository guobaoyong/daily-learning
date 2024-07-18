package qqzsh.top.addressbook.mapper;

import qqzsh.top.addressbook.entity.User;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-15 20:23
 * @Description 用户Mapper接口
 */
public interface UserMapper {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 根据username查询用户
     */
    User findByUserName(String userName);

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

}
