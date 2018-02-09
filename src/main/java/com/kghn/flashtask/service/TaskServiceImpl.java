package com.kghn.flashtask.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kghn.flashtask.model.Task;
import com.kghn.flashtask.repository.TaskRepository;
import com.kghn.flashtask.repository.UserRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	
	private final TaskRepository taskRepository; 
	
	@Autowired
	UserRepository userRepository;

	
	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public Task create(Task task) {
		
		return  taskRepository.save(task);
	}

	@Override
	public ResponseEntity<Task>  delete(Long taskid) {
		Task task = taskRepository.getBytaskId(taskid);
	    if(task == null) {
	        return ResponseEntity.notFound().build();
	    }
	    taskRepository.delete(task);
    return ResponseEntity.ok().build();
	}

	@Override
	public List<Task> findAll() {
		return (List<Task>) taskRepository.findAll();
	}

	@Override
	public ResponseEntity<Task> getBytaskId(Long taskid) {
		Task task = taskRepository.getBytaskId(taskid);
	    if(task == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(task);
	}

	@Override
	public ResponseEntity<Task>  update(Long taskid, Task taskValues) {
		Task task = taskRepository.getBytaskId(taskid);
	    if(task == null) {
	        return ResponseEntity.notFound().build();
	    }
	    task.setTitle(taskValues.getTitle());
	    task.setDiscription(taskValues.getDiscription());
	    task.setEstimate(taskValues.getEstimate());
	    task.setStatus(taskValues.getStatus());
	    task.setModifiedBy(taskValues.getModifiedBy());
	    Task taskU = taskRepository.save(task);
	    return ResponseEntity.ok(taskU);
	}

	@Override
	public Future<List<Task>> findByuserId(Long taskid) {
		return taskRepository.findByuserId(taskid);
	}

	@Override
	public Future<Stream<Task>> findByTitle(String title) {		
		return taskRepository.findByTitle(title);
	}

}
