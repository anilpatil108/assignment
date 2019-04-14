/**
 * 
 */
package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.UserDao;
import com.uxpsystems.assignment.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User findActiveUser(String userName) {
		return userDao.findActiveUser(userName);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteUser(id);
	}

}
