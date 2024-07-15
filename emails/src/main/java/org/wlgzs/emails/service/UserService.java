package org.wlgzs.emails.service;

import org.springframework.transaction.annotation.Transactional;
import org.wlgzs.emails.model.User;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-02 16:59
 * @Describe
 */
public interface UserService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(User record);

    public int insertSelective(User record);

    @Transactional
    User selectByPrimaryKey(Integer id);

    @Transactional
    int updateByPrimaryKeySelective(User record);

    @Transactional
    int updateByPrimaryKey(User record);

    @Transactional
    User selectByUserName(String userName);

    @Transactional
    User selectByEmail(String email);

    @Transactional
    User selectByUserNameOrEmail(String userNameOrEmail);
}
