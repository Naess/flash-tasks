package com.kghn.flashtask.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;

import com.kghn.flashtask.model.Task;


public interface TaskService {

	
	/**
     * Creates a new Task.
     * @param 	TaskData   
     * @return   Task data          
     */
    Task create(Task task);

    /**
     * Deletes a Task.
     * @param id    The Task id.
     * @return      deleted Task data.
     */
    ResponseEntity<Task> delete(Long taskid);

    /**
     * Finds all Tasks.
     * @return
     */
    List<Task> findAll();

    /**
     * Finds a Task by id.
     * @param  Task id
     * @return      Task data.
     */
    ResponseEntity<Task> getBytaskId(Long taskid);

    /**
     * Updates a Task.
     * @param  Task id
     * @return  Updated Task.
     */
    ResponseEntity<Task> update(Long taskid, Task task);
    
    Future<List<Task>> findByuserId(Long taskid);
    
    Future<Stream<Task>> findByTitle(String title);
}
