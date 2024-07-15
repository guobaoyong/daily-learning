package org.wlgzs.springbootshiro.service;

import org.wlgzs.springbootshiro.domain.User;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-11 11:53
 * @Describe
 */
public interface UserService {
    User findByName(String name);

    User findById(Integer id);
}
