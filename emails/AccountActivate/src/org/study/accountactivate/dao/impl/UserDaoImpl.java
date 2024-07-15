package org.study.accountactivate.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.study.accountactivate.dao.UserDao;
import org.study.accountactivate.domail.User;

public class UserDaoImpl implements UserDao {
	
	private static UserDaoImpl instance = new UserDaoImpl();
	
	private UserDaoImpl() {}
	
	public static UserDaoImpl getInstance() {
		return instance;
	}
	
	Map<Integer,User> users = new HashMap<Integer, User>();
	
	int nextId = 1;

	@Override
	public void addUser(User user) {
		user.setId(nextId++);
		user.setRandomCode(UUID.randomUUID().toString());
		users.put(user.getId(), user);
	}

	@Override
	public void updateUser(User user) {
		users.put(user.getId(), user);
	}

	@Override
	public User findUserById(int id) {
		return users.get(id);
	}

	@Override
	public User findUserByName(String userName) {
		Collection<User> userValues = users.values();
		for (Iterator<User> iterator = userValues.iterator();iterator.hasNext();) {
			User user = iterator.next();
			if (user.getUserName().equals(userName)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findUserByNameOrEmail(String nameOrEmail) {
		Collection<User> userValues = users.values();
		for(Iterator<User> iterator = userValues.iterator();iterator.hasNext();) {
			User user = iterator.next();
			if (user.getEmail().equals(nameOrEmail) || user.getUserName().equals(nameOrEmail)) {
				return user;
			}
		}
		return null;
	}

}
