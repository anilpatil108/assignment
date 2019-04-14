package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.uxpsystems.assignment.entity.User;

public interface UserService {
	@Secured ({"ROLE_ADMIN", "ROLE_USER"})
	User findActiveUser(String userName);
	@Secured ({"ROLE_ADMIN", "ROLE_USER"})
	List<User> findAllUsers();
	@Secured ({"ROLE_ADMIN", "ROLE_USER"})
	User getUserById(Long id);
	@Secured ({"ROLE_ADMIN"})
	User saveUser(User user);
	@Secured ({"ROLE_ADMIN"})
	void deleteUser(Long id);
}
