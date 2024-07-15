package org.study.accountactivate.dao;

import org.study.accountactivate.domail.User;

public interface UserDao {

	void addUser(User user);
	
	void updateUser(User user);
	
	User findUserById(int id);
	
	User findUserByName(String userName);
	
	User findUserByNameOrEmail(String nameOrEmail);
	
}
