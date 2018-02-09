package com.kghn.flashtask.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;

import com.kghn.flashtask.model.TodoList;



public interface TodoListService {

	
	/**
     * Creates a new TodoList.
     * @param 	TodoList data   
     * @return   TodoList data          
     */
    TodoList create(String title, Long userId);

    /**
     * Deletes a TodoList.
     * @param id    The TodoList id.
     * @return      deleted TodoList data.
     */
    ResponseEntity<TodoList> delete(Long listid);

    /**
     * Finds all TodoList.
     * @return
     */
    List<TodoList> findAll();

    /**
     * Finds a TodoList by id.
     * @param  Task id
     * @return      Task data.
     */
    ResponseEntity<TodoList> findBylistId(Long listid);

    /**
     * Updates a TodoList.
     * @param  TodoList id
     * @return  Updated TodoList.
     */
    ResponseEntity<TodoList> update(Long listid, TodoList task);
    
    /**
     * Find  TodoList by user Id.
     * @param  TodoList id
     * @return   TodoLists.
     */
    Future<List<TodoList>> findByuserId(Long listid);
    
    /**
     * Find  TodoList by list title.
     * @param  TodoList title
     * @return   TodoLists.
     */
    Future<Stream<TodoList>> findByTitle(String title);
}
