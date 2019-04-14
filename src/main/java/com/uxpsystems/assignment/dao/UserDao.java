package com.uxpsystems.assignment.dao;

import java.util.List;

import com.uxpsystems.assignment.entity.User;

public interface UserDao {
	
	User findActiveUser(String userName);
	List<User> findAllUsers();
	User getUserById(Long id);
	User saveUser(User user);
	void deleteUser(Long id);

}
