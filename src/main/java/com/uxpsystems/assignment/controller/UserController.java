package com.uxpsystems.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> retrieveAllUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable long id) {
		User user = userService.getUserById(id);

		if (user==null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);

		return ResponseEntity.ok(savedUser);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id) {

		User userOptional = userService.getUserById(id);

		if (userOptional==null)
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		User savedUser = userService.saveUser(user);

		return ResponseEntity.ok(savedUser);
	}
	

}
