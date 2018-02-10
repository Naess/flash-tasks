/* CRUD Operations */
package com.kghn.flashtask.repository;

import java.util.List;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import com.kghn.flashtask.model.User;

public interface UserRepository extends Repository<User, Long> {

	void delete(User deleted);

	List<User> findAll();

	@Async
	User getByuserId(long id);

	User save(User user);

	ResponseEntity<User> findOne(Long id);

	/* find by sent token */
	User getByEmail(String email);
	
	ResponseEntity<User> findByEmail(String email);

	/* find by sent token */
	User getByToken(String token);


}
