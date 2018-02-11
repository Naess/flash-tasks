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
    ResponseEntity<User> delete(long userid);

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
    ResponseEntity<User> findByuserId(long userid);

    /**
     * Updates a user data.
     * @param update user data
     * @return                 updated user data.
     */
    ResponseEntity<User> update(long userid, User user);	
    
    
    /**
     * Updates a user data partially.
     * @param update user data
     * @return                 updated user data.
     */
    ResponseEntity<User> partialUpdate(long userid, User user);	
    
    
    /**
     * Find user by email and password
     * @param email
     * @param password
     * @return
     */
    
    ResponseEntity<User> getByEmail(String email, String passwd);
    /**
     * Find user by confirmation token
     * @param token string
     * @return user data
     */
    ResponseEntity<User> getByToken(String token);
}
