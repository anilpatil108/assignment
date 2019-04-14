package com.uxpsystems.assignment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.service.UserService;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserControllerTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testRetrieveAllUsers() {
		
	}
	
	public ResponseEntity<User> retrieveUser(@PathVariable long id) {
		User user = userService.getUserById(id);

		if (user==null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(user);
	}
	
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
	
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);

		return ResponseEntity.ok(savedUser);

	}
	
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id) {

		User userOptional = userService.getUserById(id);

		if (userOptional==null)
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		User savedUser = userService.saveUser(user);

		return ResponseEntity.ok(savedUser);
	}

}
