package com.kghn.flashtask.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kghn.flashtask.model.Task;
import com.kghn.flashtask.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public Task create(Task task) {

		return taskRepository.save(task);
	}

	@Override
	public ResponseEntity<Task> delete(long id) {
		Task task = taskRepository.getById(id);
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
	public ResponseEntity<Task> getBytaskId(long id) {
		Task task = taskRepository.getById(id);
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(task);
	}
	/*Update user data */
	@Override
	public ResponseEntity<Task> update(long id, Task taskValues) {
		Task task = taskRepository.getById(id);
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
	@Override
	public Future<Stream<Task>> findByTitle(String title) {
		return taskRepository.findByTitle(title);
	}

	/* Partially update user data */
	@Override
	public ResponseEntity<Task> partialUpdate(long id, Task taskValue) {
		Task task = taskRepository.getById(id);
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		if (taskValue.getTitle()!= null) {
			task.setTitle(taskValue.getTitle());
		}
		if (taskValue.getDiscription()!= null) {
			task.setDiscription(taskValue.getDiscription());
		}
		if (taskValue.getEstimate()!= null) {
			task.setEstimate(taskValue.getEstimate());
		}
		if (taskValue.getStatus()!= null) {
			task.setStatus(taskValue.getStatus());
		}
		Task taskU = taskRepository.save(task);
		return ResponseEntity.ok(taskU);
	}

}
