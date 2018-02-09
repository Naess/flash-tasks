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

	@Autowired
	private final ListRepository listRepository;
	
	
	
	public TodoListServiceImpl(ListRepository listRepository) {
		super();
		this.listRepository = listRepository;
	}

	@Override
	public TodoList create(String title, Long userId) {
		
		TodoList list = new TodoList();
		list.setTitle(title);
		list.setUserId(userId);		
		return listRepository.save(list);
	}

	@Override
	public ResponseEntity<TodoList> delete(Long listid) {
		TodoList list = listRepository.getBylistId(listid);
		if(list == null) {
			ResponseEntity.notFound().build();
		}
		listRepository.delete(list);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<TodoList> findAll() {
		
		return (List<TodoList>) listRepository.findAll();
	}

	@Override
	public ResponseEntity<TodoList> findBylistId(Long listid) {
		
		TodoList list = listRepository.getBylistId(listid);
		if(list == null) {
			ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok().body(list);
	}

	@Override
	public ResponseEntity<TodoList> update(Long listid, TodoList listValue) {
		
		TodoList list = listRepository.getBylistId(listid);
		if(list == null) {
			ResponseEntity.notFound().build();
		}
		list.setBuddyId(listValue.getBuddyId());
		list.setSharable(listValue.isSharable());
		list.setTitle(listValue.getTitle());
		list.setTaskId(listValue.getTaskId());
		list.setToken(listValue.getToken());
		TodoList listU = listRepository.save(list);
		return ResponseEntity.ok(listU);
	}

	@Override
	public Future<List<TodoList>> findByuserId(Long id) {
		
		return listRepository.findByuserId(id);
	}

	@Override
	public Future<Stream<TodoList>> findByTitle(String title) {
		
		return listRepository.findByTitle(title);
	}

}
