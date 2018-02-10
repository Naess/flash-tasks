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
import org.springframework.web.bind.annotation.PatchMapping;
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
	@GetMapping("/tasks/{taskid}")
	public ResponseEntity<Task> findTaskById(@PathVariable(value = "taskid") long id) {
		return taskService.getBytaskId(id);
	}

	// Update a Task
	@PutMapping("/tasks/{taskid}")
	public ResponseEntity<Task> updateTask(@PathVariable(value = "taskid") long id,
			@Valid @RequestBody Task taskValues) {
		return taskService.update(id, taskValues);
	}

	// Delete a Task
	@DeleteMapping("/tasks/{taskid}")
	public ResponseEntity<Task> deleteTask(@PathVariable(value = "taskid") long id) {
		return taskService.delete(id);
	}

	// Get List of Tasks by UserId

	// Update a Task
	@PatchMapping("/tasks/{taskid}")
	public ResponseEntity<Task> partiallUpdate(@PathVariable(value = "taskid") long id,
			@Valid @RequestBody Task taskValues) {
		return taskService.update(id, taskValues);
	}

	@GetMapping("/tasks/{searchTitle}")
	public Future<Stream<Task>> findByTitle(String title) {
		return taskService.findByTitle(title);
	}
}
