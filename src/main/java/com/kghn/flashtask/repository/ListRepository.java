package com.kghn.flashtask.repository;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import com.kghn.flashtask.model.Task;
import com.kghn.flashtask.model.TodoList;



public interface ListRepository extends Repository<TodoList, Long>{

	
	void delete(TodoList deleted);

    List<TodoList> findAll();

 
    @Async
    TodoList getBylistId(Long id);
   
    @Async
    Future<List<TodoList>> findByuserId(Long listid);
    
    TodoList save(TodoList list);

    ResponseEntity<TodoList>  findBylistId(Long listId);
    
    Future<List<Task>> findBytaskId(Set<Long> taskid);
     
    @Async
    Future<Stream<TodoList>> findByTitle(String title);
}
