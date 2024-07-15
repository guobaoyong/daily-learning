package org.wlgzs.springbootshiro.dao;

import org.wlgzs.springbootshiro.domain.User;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-11 11:44
 * @Describe
 */
public interface UserDao {
    User findByName(String name);

    User findById(Integer id);
}
