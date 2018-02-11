package com.kghn.flashtask.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kghn.flashtask.model.Task;
import com.kghn.flashtask.model.TodoList;
import com.kghn.flashtask.repository.ListRepository;
import com.kghn.flashtask.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final ListRepository listRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository, ListRepository listRepository) {
		super();
		this.taskRepository = taskRepository;
		this.listRepository = listRepository;
	}

	@Override
	public Task create(Task task) {

		return taskRepository.save(task);
	}

	@Override
	public ResponseEntity<Task> delete(long id) {
		Task task = taskRepository.getBytaskId(id);
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		taskRepository.delete(task);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<Task> findAll() {
		return (List<Task>) taskRepository.findAll();
	}

	/* Create data entry in database */
	@Override
	public ResponseEntity<Task> findBytaskId(long id) {
		Task task = taskRepository.getBytaskId(id);
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(task);
	}

	/* Update user data */
	@Override
	public ResponseEntity<Task> update(long id, Task taskValues) {
		Task task = taskRepository.getBytaskId(id);
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		task.setTitle(taskValues.getTitle());
		task.setDiscription(taskValues.getDiscription());
		task.setEstimate(taskValues.getEstimate());
		task.setStatus(taskValues.getStatus());
		Task taskU = taskRepository.save(task);
		return ResponseEntity.ok(taskU);
	}

	/* search task by title */
	@Transactional(readOnly = true)
	@Override
	public  ResponseEntity<List<Task>> findByTitle(String title) {
		List<Task> lists= taskRepository.findByTitle(title);
		if(lists == null) {
			ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(lists);
	}

	/* Partially update user data */
	@Override
	public ResponseEntity<Task> partialUpdate(long id, Task taskValue, Optional<Long> listid) {
		TodoList list = new TodoList();
		if(listid.isPresent()) {
			list = listRepository.getBylistId(listid.get());
		}
		
		Task task = taskRepository.getBytaskId(id);
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		if (taskValue.getTitle() != null) {
			task.setTitle(taskValue.getTitle());
		}
		if (taskValue.getDiscription() != null) {
			task.setDiscription(taskValue.getDiscription());
		}
		if (taskValue.getEstimate() != null) {
			task.setEstimate(taskValue.getEstimate());
		}
		if (taskValue.getStatus() != null) {
			task.setStatus(taskValue.getStatus());
		}
		if(list != null)
		{
			Set<Task> tasks = new HashSet<Task>();
			new TodoList(list.getTitle(), tasks, list.getUsers());
		}
		Task taskU = taskRepository.save(task);
		return ResponseEntity.ok(taskU);
	}

}
