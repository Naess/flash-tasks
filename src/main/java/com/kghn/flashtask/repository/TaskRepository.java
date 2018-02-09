package com.kghn.flashtask.repository;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import com.kghn.flashtask.model.Task;


public interface TaskRepository extends Repository<Task, Long> {
	
	
	void delete(Task deleted);

    List<Task> findAll();
     
    @Async 
    Task getBytaskId(Long taskId);
   
    @Async
    Future<List<Task>> findByuserId(Long userid);
    
    Task save(Task task);

    ResponseEntity<Task>  findBytaskId(Long taskId);
     
    @Async
    @Query("SELECT t.title FROM Task t where t.title = :title") 
    Future<Stream<Task>> findByTitle(String title);
}
