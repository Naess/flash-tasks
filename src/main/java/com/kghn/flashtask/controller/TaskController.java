/***
 *  List controller class to expose the API
 *  
 *  kghn
 */
package com.kghn.flashtask.controller;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kghn.flashtask.model.Task;
import com.kghn.flashtask.service.TaskService;

@RestController
@RequestMapping("api/v1/")
public class TaskController {
	
	@Autowired
	TaskService taskService; 

	
	// Get All Tasks
	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
	    return (List<Task>) taskService.findAll();
	}

    // Create a new Task
	@PostMapping("/tasks")
	public Task createTask(@Valid @RequestBody Task task) {
	    return taskService.create(task);
	}

    // Get a Task
	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> findTaskById(@PathVariable(value = "id") Long id) {
		return taskService.getBytaskId(id);
	}

    // Update a Task
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long id, 
	                                       @Valid @RequestBody Task taskValues) {
	   return taskService.update(id, taskValues);
	}

    // Delete a Task
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable(value = "id") Long id) { 
	    return taskService.delete(id);
	}
	
	//Get List of Tasks by UserId
	
	@GetMapping("/tasks/list/{id}")
	public Future<List<Task>> getTaskByUserId(@PathVariable(value = "id") Long id){
		return taskService.findByuserId(id);
	}
	@GetMapping("/tasks/{searchTitle}")
	public Future<Stream<Task>> findByTitle(String title) {		
		return taskService.findByTitle(title);
	}
}
