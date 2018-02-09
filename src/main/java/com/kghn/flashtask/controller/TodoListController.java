/***
 *  List controller class to expose the API
 *  
 *  kghn
 */

package com.kghn.flashtask.controller;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kghn.flashtask.model.TodoList;
import com.kghn.flashtask.service.TodoListService;

@RestController
@RequestMapping("api/v1/")
public class TodoListController {

	

	@Autowired
	TodoListService listService;

	// Create a new list
	@PostMapping("/list")
	public TodoList create(String title, Long userId) {

		return listService.create(title, userId);
	}

	// Delete a list
	@DeleteMapping("/list/{id}")
	public ResponseEntity<TodoList> delete(Long id) {
		return listService.delete(id);
	}

	// Get All lists
	@GetMapping("/lists")
	List<TodoList> findAll() {
		return listService.findAll();
	}

	// Get a list
	@GetMapping("/list/{id}")
	ResponseEntity<TodoList> findBylistId(Long id) {
		return listService.findBylistId(id);
	}

	// Update a list
	@PutMapping("/list/{id}")
	ResponseEntity<TodoList> update(Long id, TodoList list) {
		return listService.update(id, list);
	}

	// Get List of Tasks by UserId

	@GetMapping("/list/lists/{id}")
	Future<List<TodoList>> findByuserId(Long id) {
		return listService.findByuserId(id);
	}

	@GetMapping("/list/{searchTitle}")
	Future<Stream<TodoList>> findByTitle(String title) {
		return listService.findByTitle(title);
	}

}
