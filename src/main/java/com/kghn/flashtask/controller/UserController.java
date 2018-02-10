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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	
	private UserService userService;


	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
		
	}

	// Api call for Get All Users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return (List<User>) userService.findAll();
	}

	//Api call for  Get a User
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userid) {

		return userService.findByuserId(userid);
	}

	//Api call for  Update a User
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUse(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
		return userService.update(id, user);
	}
	
	// Api call for Partial Update a User
		@PatchMapping("/users/{id}")
		public ResponseEntity<User> partialUpdate(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
			return userService.partialUpdate(id, user);
		}

	// Api call for Delete a User
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUse(@PathVariable(value = "id") Long id) {

		return userService.delete(id);
	}

	// Api call for login
	@PostMapping("/users/sign-in")
	
		public ResponseEntity<User> signIn(@RequestBody User userValue) {
		return userService.getByEmail(userValue);
	}

	

	@PostMapping("/users/sign-up")
	public User signUp(@RequestBody User user) {
		return userService.create(user);
	}
}
