/***
 *  User controller class to expose the API
 *  
 *  kghn
 */

package com.kghn.flashtask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kghn.flashtask.model.User;
import com.kghn.flashtask.service.UserService;

@RestController
@RequestMapping("api/v1/")
public class UserController {

	//@Autowired
	private UserService userService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Autowired
	public UserController(UserService userService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// Get All Users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return (List<User>) userService.findAll();
	}

	// Create a User profile
	@PostMapping("/users")
	public User createTask(@Valid @RequestBody User user) {
		return userService.create(user);
	}

	// Get a User
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userid) {

		return userService.findByuserId(userid);
	}

	// Update a User
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUse(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
		return userService.update(id, user);
	}

	// Delete a User
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUse(@PathVariable(value = "id") Long id) {

		return userService.delete(id);
	}

	@PostMapping("/users/{email}/{password}")
	User login(@PathVariable String email, @PathVariable String password) {
		return userService.getByEmail(email, password);
	}

	

	@PostMapping("/users/sign-up")
	public void signUp(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.create(user);
	}
}
