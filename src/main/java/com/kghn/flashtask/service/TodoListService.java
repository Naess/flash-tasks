package com.kghn.flashtask.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import com.kghn.flashtask.model.TodoList;

public interface TodoListService {

	/**
	 * Creates a new TodoList.
	 * 
	 * @param TodoList
	 *            data
	 * @return TodoList data
	 */
	ResponseEntity<TodoList> create(String title, Optional<Long> taskId, Optional<Long> userId);

	/**
	 * Deletes a TodoList.
	 * 
	 * @param id
	 *            The TodoList id.
	 * @return deleted TodoList data.
	 */
	ResponseEntity<TodoList> delete(long listid);

	/**
	 * Finds all TodoList.
	 * 
	 * @return
	 */
	List<TodoList> findAll();

	/**
	 * Finds a TodoList by id.
	 * 
	 * @param Task
	 *            id
	 * @return Task data.
	 */
	ResponseEntity<TodoList> findBylistId(long listid);

	/**
	 * Updates a TodoList.
	 * 
	 * @param TodoList
	 *            id
	 * @return Updated TodoList.
	 */
	ResponseEntity<TodoList> update(long listid, TodoList listValue);

	/**
	 * Partial Updates a TodoList.
	 * 
	 * @param TodoList
	 *            id, optional taskId and userId
	 * @return Updated TodoList.
	 */
	ResponseEntity<TodoList> partialUpdate(long id, TodoList listValue, Optional<Long> taskId, Optional<Long> userId);

	/**
	 * Partial Updates a TodoList.
	 * 
	 * @param TodoList
	 *            id
	 * @return Updated TodoList.
	 */
	ResponseEntity<TodoList> partialUpdate(long id, TodoList listValue);

	/**
	 * Find TodoList by list title.
	 * 
	 * @param TodoList
	 *            title
	 * @return TodoLists.
	 */
	ResponseEntity<List<TodoList>> findByTitle(String title);

	/**
	 * Find invitation token
	 * 
	 * @param token
	 *            string
	 * @return list data
	 */
	ResponseEntity<TodoList> getByToken(String token);

	/**
	 * Send share invitation token
	 * 
	 * @param token
	 *            string
	 * @param list
	 *            id
	 * @return list data
	 */
	ResponseEntity<String> Sharelist(String email, Long id, HttpServletRequest request);

	/**
	 * Find TodoList by list user.
	 * 
	 * @param TodoList
	 *            title
	 * @return TodoLists.
	 */
	ResponseEntity<List<TodoList>> findByUserId(long userId);

}
