/**
 * User Service implementations which process the business logics. 
 */

package com.kghn.flashtask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kghn.flashtask.model.User;
import com.kghn.flashtask.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	// Spring dependency injection based on constructor
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	/* Create method to insert data in the repository It also encrypt the data */

	@Override
	public User create(User userValue) {
		User user = new User();
		user.setActive(userValue.isActive());
		user.setFirstname(userValue.getFirstname());
		user.setLastname(userValue.getLastname());
		user.setEmail(userValue.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(userValue.getPassword()));
		return userRepository.save(user);
	}

	/* delete method to remove data from the repository */
	@Override
	public ResponseEntity<User> delete(long id) {
		User user = userRepository.getByuserId(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}

	/* find all Users */
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	/* find user by Id */
	@Override
	public ResponseEntity<User> findByuserId(long id) {
		User user = userRepository.getByuserId(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	/* Update all the data for a user */
	@Override
	public ResponseEntity<User> update(long id, User userValue) {
		User user = userRepository.getByuserId(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setActive(userValue.isActive());
		user.setLastname(user.getLastname());
		user.setFirstname(user.getFirstname());
		user.setPassword(new BCryptPasswordEncoder().encode(userValue.getPassword()));

		User userU = userRepository.save(user);

		return ResponseEntity.ok(userU);
	}

	/* search and authenticate users */
	@Override
	public ResponseEntity<User> getByEmail(String email, String passwd) {

		User user = userRepository.getByEmail(email);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		String pwd = passwd;

		Boolean result = new BCryptPasswordEncoder().matches(pwd, user.getPassword());

		if (!result) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(user);

	}

	/* search if the user has a legitimate invitation */
	@Override
	public ResponseEntity<User> getByToken(String token) {

		User user = userRepository.getByToken(token);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	/* Partially update user data */
	@Override
	public ResponseEntity<User> partialUpdate(long id, User userValue) {

		User user = userRepository.getByuserId(id);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		if (userValue.getPassword() != null) {
			user.setPassword(new BCryptPasswordEncoder().encode(userValue.getPassword()));
		}
		if (userValue.isActive() != false) {
			user.setActive(true);
		}
		if (userValue.getFirstname() != null) {
			user.setFirstname(userValue.getFirstname());
		}
		if (userValue.getLastname() != null) {
			user.setLastname(userValue.getLastname());
		}
		if (userValue.getToken() != null) {
			user.setToken(userValue.getToken());
		}
		User userU = userRepository.save(user);

		return ResponseEntity.ok(userU);

	}

}
