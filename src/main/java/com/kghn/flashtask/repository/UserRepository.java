package com.kghn.flashtask.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;


import com.kghn.flashtask.model.User;

public interface UserRepository extends Repository<User, Long>{
	
	
	void delete(User deleted);

    List<User> findAll();

    @Async
    User getByuserId(Long userid);
    
    User save(User user);

    ResponseEntity<User>  findOne(Long userid);
    
    @Async
    @Query("SELECT email, password FROM User t where t.email like :email and t.password like :password") 
    User getByEmail(String email, String password);
    
    User findBytoken(String token);

}
