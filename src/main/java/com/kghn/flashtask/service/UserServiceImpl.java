package com.kghn.flashtask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kghn.flashtask.model.User;
import com.kghn.flashtask.repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService {


	private final UserRepository userRepository;
	
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public ResponseEntity<User> delete(Long id) {
		User user = userRepository.getByuserId(id);
		if(user ==null) {
			return ResponseEntity.notFound().build();
		}
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<User> findAll() {
		return(List<User>) userRepository.findAll();
	}

	@Override
	public ResponseEntity<User> findByuserId(Long id) {
		User user = userRepository.getByuserId(id);
		if(user ==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);	
	}

	@Override
	public ResponseEntity<User> update(Long id, User userValue) {
		User user = userRepository.getByuserId(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setActive(userValue.isActive());
		user.setLastName(userValue.getLastName());
		user.setFirstName(userValue.getFirstName());
		user.setPassword(userValue.getPassword());
		
		User userU = userRepository.save(user);
		
		return ResponseEntity.ok(userU);
	}

	@Override
	public User getByEmail(String email, String password) {
		
		return userRepository.getByEmail(email, password);
	}

}
