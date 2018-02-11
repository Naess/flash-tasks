/* CRUD Operations */
package com.kghn.flashtask.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import com.kghn.flashtask.model.Task;


public interface TaskRepository extends Repository<Task, Long> {
	
	
	void delete(Task deleted);

    List<Task> findAll();
     
    @Async 
    Task getBytaskId(long id);
    
    
    Task save(Task task);

    ResponseEntity<Task>  findBytaskId(long id);
     
    /* Query using normal query title  */
	@Async
	@Query(value = "SELECT * FROM task t where LOWER(t.title) LIKE LOWER(:title)", nativeQuery=true)
	List<Task> findByTitle(@Param("title") String title);
}
