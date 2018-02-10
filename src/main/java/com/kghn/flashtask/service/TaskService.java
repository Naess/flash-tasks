package com.kghn.flashtask.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;

import com.kghn.flashtask.model.Task;

public interface TaskService {

	/**
	 * Creates a new Task.
	 * 
	 * @param TaskData
	 * @return Task data
	 */
	Task create(Task task);

	/**
	 * Deletes a Task.
	 * 
	 * @param  task id
	 *            
	 * @return deleted Task data.
	 */
	ResponseEntity<Task> delete(long id);

	/**
	 * Finds all Tasks.
	 * 
	 * @return
	 */
	List<Task> findAll();

	/**
	 * Finds a Task by id.
	 * 
	 * @param Task id
	 *            
	 * @return Task data.
	 */
	ResponseEntity<Task> findBytaskId(long id);

	/**
	 * Updates a Task.
	 * 
	 * @param Task id, change values
	 *            
	 * @return Updated Task.
	 */
	ResponseEntity<Task> update(long id, Task task);
	/**
	 * Find Task by the task title.
	 * 
	 * @param title string 
	 *            
	 * @return Task.
	 */
	Future<Stream<Task>> findByTitle(String title);

	/**
	 * Partially Updates a Task.
	 * 
	 * @param Task id and change values
	 *            
	 * @return Updated Task.
	 */
	ResponseEntity<Task> partialUpdate(long listid, Task taskValue);
}
