/**
 * 
 */
package com.uxpsystems.assignment.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.entity.UserStatus;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository repository;

	@Override
	public User findActiveUser(String userName) {
		return repository.findUserByStatusAndName(UserStatus.Activated, userName);
	}

	@Override
	public List<User> findAllUsers() {
		return repository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return repository.getOne(id);
	}

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		repository.delete(getUserById(id));
	}

}
