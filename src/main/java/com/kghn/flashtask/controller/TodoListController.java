/***
 *  List controller class to expose the API
 *  
 *  kghn
 */

package com.kghn.flashtask.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kghn.flashtask.model.TodoList;
import com.kghn.flashtask.service.TodoListService;

@RestController
@RequestMapping("api/v1/")
public class TodoListController {

	@Autowired
	TodoListService listService;

	// Api call for Create a new list
	@PostMapping("/list")
	public ResponseEntity<TodoList> create(@RequestParam(value = "title") String title,
			@RequestParam(value = "taskId", required = false) Optional<Long> taskId,
			@RequestParam(value = "userId", required = false) Optional<Long> userId) {

		return listService.create(title, taskId, userId);
	}

	// Api call for Delete a list
	@DeleteMapping("/list/{listId}")
	public ResponseEntity<TodoList> delete(long listId) {
		return listService.delete(listId);
	}

	// Api call for Get All lists
	@GetMapping("/lists")
	List<TodoList> findAll() {
		return listService.findAll();
	}

	// Api call for Get a list
	@GetMapping("/list/{listId}")
	ResponseEntity<TodoList> findBylistId(long listId) {
		return listService.findBylistId(listId);
	}

	// Api call for Update a list
	@PutMapping("/list/{listId}")
	ResponseEntity<TodoList> update(long listId, TodoList list) {
		return listService.update(listId, list);
	}

	// Api call for to search list by title
	@GetMapping("/list/{searchTitle}")
	ResponseEntity<List<TodoList>> findByTitle(String title) {
		return listService.findByTitle(title);
	}

	// Api call for Update a list
	@PatchMapping("/list/{id}")
	public ResponseEntity<TodoList> partiallUpdate(@RequestParam(value = "id") Long id,
			@Valid @RequestBody TodoList list, @RequestParam("taskId") Optional<Long> taskId,
			@RequestParam("userId") Optional<Long> userId) {
		return listService.partialUpdate(id, list, taskId, userId);
	}

	// Api call for to search list by token
	@GetMapping("/list/{token}")
	ResponseEntity<TodoList> findByToken(String token) {
		return listService.getByToken(token);
	}

	// Api call for to send sharelink to other users
	@PostMapping("/list/{sharelist}")
	ResponseEntity<String> ShareList(@RequestParam("email") String email, @RequestParam("id") Long id,
			HttpServletRequest request) {

		return listService.Sharelist(email, id, request);

	}

	// Api call for to search list by userId
	@PatchMapping("/list/{userId}")
	ResponseEntity<List<TodoList>> findByUserId(@RequestParam("userId") Long userId) {

		return listService.findByUserId(userId);
	}

}
