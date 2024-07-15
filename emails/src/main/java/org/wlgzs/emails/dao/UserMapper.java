package org.wlgzs.emails.dao;


import org.wlgzs.emails.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String userName);

    User selectByEmail(String email);

    User selectByUserNameOrEmail(String userNameOrEmail);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}