package com.kghn.flashtask.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.kghn.flashtask.model.User;

;

public interface UserService {

	
	/**
     * Creates a new User.
     * @param user data.
     * @return       a user database data.
     */
    User create(User user);

    /**
     * Deletes User.
     * @param  user id
     * @return     deleted user info..
     */
    ResponseEntity<User> delete(Long userid);

    /**
     * Finds a user all user.
     * @return user list
     */
    List<User> findAll();

    /**
     * Finds a user by id.
     * @param  id of a user
     * @return      user data.
     */
    ResponseEntity<User> findByuserId(Long userid);

    /**
     * Updates a user data.
     * @param update user data
     * @return                 updated user data.
     */
    ResponseEntity<User> update(Long userid, User user);	
    
    /**
     * Find user by email and password
     * @param email
     * @param password
     * @return
     */
    
    User getByEmail(String email, String password);
}
