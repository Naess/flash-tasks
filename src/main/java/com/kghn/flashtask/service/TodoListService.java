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
    TodoList create(String title);

    /**
     * Deletes a TodoList.
     * @param id    The TodoList id.
     * @return      deleted TodoList data.
     */
    ResponseEntity<TodoList> delete(long listid);

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
    ResponseEntity<TodoList> findBylistId(long listid);

    /**
     * Updates a TodoList.
     * @param  TodoList id
     * @return  Updated TodoList.
     */
    ResponseEntity<TodoList> update(long listid, TodoList listValue);
    
    /**
     * Partial Updates a TodoList.
     * @param  TodoList id
     * @return  Updated TodoList.
     */
    ResponseEntity<TodoList> partialUpdate(long listid, TodoList listValue);
    
    /**
     * Find  TodoList by list title.
     * @param  TodoList title
     * @return   TodoLists.
     */
    Future<Stream<TodoList>> findByTitle(String title);
}
