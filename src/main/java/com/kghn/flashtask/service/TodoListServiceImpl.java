/**
 * User Service implementations which process the business logics. 
 */

package com.kghn.flashtask.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kghn.flashtask.model.TodoList;
import com.kghn.flashtask.repository.ListRepository;

@Service("listService")
public class TodoListServiceImpl implements TodoListService {
	//Spring dependency injection based on constructor
	@Autowired
	private final ListRepository listRepository;
	
	
	
	public TodoListServiceImpl(ListRepository listRepository) {
		super();
		this.listRepository = listRepository;
	}

	 /* Create method to insert data in the repository It also encrypt the data */
	@Override
	public TodoList create(String title) {
		
		TodoList list = new TodoList();
		list.setTitle(title);	
		return listRepository.save(list);
	}
	 /* delete method to remove data from the repository*/
	@Override
	public ResponseEntity<TodoList> delete(long listid) {
		TodoList list = listRepository.getById(listid);
		if(list == null) {
			ResponseEntity.notFound().build();
		}
		listRepository.delete(list);
		return ResponseEntity.ok().build();
	}
	 /* find all lists */
	@Override
	public List<TodoList> findAll() {
		
		return (List<TodoList>) listRepository.findAll();
	}
	/* find list by Id */
	@Override
	public ResponseEntity<TodoList> findBylistId(long id) {
		
		TodoList list = listRepository.getById(id);
		if(list == null) {
			ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok().body(list);
	}
	/* Update all the data for a list*/
	@Override
	public ResponseEntity<TodoList> update(long id, TodoList listValue) {
		
		TodoList list = listRepository.getById(id);
		if(list == null) {
			ResponseEntity.notFound().build();
		}
		
		list.setSharable(listValue.isSharable());
		list.setTitle(listValue.getTitle());
		list.setToken(listValue.getToken());
		list.setTasks(listValue.getTasks());
		TodoList listU = listRepository.save(list);
		return ResponseEntity.ok(listU);
	}

    /* find by list title */
	@Override
	public Future<Stream<TodoList>> findByTitle(String title) {
		
		return listRepository.findByTitle(title);
	}
	/* Update partialdata the data for a list*/
	@Override
	public ResponseEntity<TodoList> partialUpdate(long id, TodoList listValue) {
		TodoList list = listRepository.getById(id);
		if(list == null) {
			ResponseEntity.notFound().build();
		}
		if(listValue.isSharable() != false) {
			list.setSharable(true);
		}
		if(listValue.getTitle() != null) {
			list.setTitle(listValue.getTitle());
		}
		if(listValue.getToken() != null) {
			list.setToken(listValue.getToken());
		}
		list.setToken(listValue.getToken());
		TodoList listU = listRepository.save(list);
		return ResponseEntity.ok(listU);
	}

	

}
