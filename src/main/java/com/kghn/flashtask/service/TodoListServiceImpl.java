/**
 * User Service implementations which process the business logics. 
 */

package com.kghn.flashtask.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kghn.flashtask.model.Task;
import com.kghn.flashtask.model.TodoList;
import com.kghn.flashtask.model.User;
import com.kghn.flashtask.repository.ListRepository;
import com.kghn.flashtask.repository.TaskRepository;
import com.kghn.flashtask.repository.UserRepository;

@Service("listService")
public class TodoListServiceImpl implements TodoListService {
	// Spring dependency injection based on constructor

	private final ListRepository listRepository;
	private final UserRepository userRepository;
	private final EmailService emailService;
	private final TaskRepository taskRepository;

	@Autowired
	public TodoListServiceImpl(ListRepository listRepository, EmailService emailService, UserRepository userRepository,
			TaskRepository taskRepository) {
		super();
		this.listRepository = listRepository;
		this.emailService = emailService;
		this.userRepository = userRepository;
		this.taskRepository = taskRepository;
	}

	/* Create method to insert data in the repository It also encrypt the data */
	@Override
	public ResponseEntity<TodoList> create(String title, Optional<Long> taskId, Optional<Long> userId) {

		/* get the user if userId null pass */
		User user = new User();
		Task task = new Task();
		Set<User> userv = new HashSet<>();
		Set<Task> taskv = new HashSet<>();
		if (userId.isPresent()) {
			user = userRepository.getByuserId(userId.get());
			userv.add(user);
		}
		/* get the task userId null pass */

		if (taskId.isPresent()) {
			task = taskRepository.getBytaskId(taskId.get());
			taskv.add(task);
		}
		TodoList listtosave = new TodoList(title);
		if (userId.isPresent() | taskId.isPresent()) {
			listtosave = new TodoList(title, taskv, userv);
		} else {
			listtosave = new TodoList(title);
		}

		TodoList listU = listRepository.save(listtosave);
		return ResponseEntity.ok().body(listU);
	}

	/* delete method to remove data from the repository */
	@Override
	public ResponseEntity<TodoList> delete(long listid) {
		TodoList list = listRepository.getBylistId(listid);
		if (list == null) {
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

		TodoList list = listRepository.getBylistId(id);
		if (list == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(list);
	}

	/* Update all the data for a list */
	@Override
	public ResponseEntity<TodoList> update(long id, TodoList listValue) {

		TodoList list = listRepository.getBylistId(id);
		if (list == null) {
			ResponseEntity.notFound().build();
		}
		TodoList listU = listRepository.save(list);
		return ResponseEntity.ok(listU);
	}

	/* find by list title */
	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<List<TodoList>> findByTitle(String title) {
		List<TodoList> lists = listRepository.findByTitle(title);
		if (lists == null) {
			ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(lists);
	}

	/* Update partial values of a list and add/replace task and users */
	@Override
	public ResponseEntity<TodoList> partialUpdate(long id, TodoList listValue, Optional<Long> taskId,
			Optional<Long> userId) {

		/* get the list */
		TodoList list = listRepository.getBylistId(id);
		/* get the user */
		User user = new User();
		if (userId.get() != null) {
			user = userRepository.getByuserId(userId.get());
		}
		/* get the task */
		Task task = new Task();
		if (taskId.get() != null) {
			task = taskRepository.getBytaskId(taskId.get());
		}

		Set<User> userv = new HashSet<>();
		Set<Task> taskv = new HashSet<>();

		/* if user update this value it will be committe the repository */
		if (list == null) {
			ResponseEntity.notFound().build();
		}
		if (listValue.isSharable() != false) {
			list.setSharable(true);
		}
		if (listValue.getTitle() != null) {
			list.setTitle(listValue.getTitle());
		}
		if (listValue.getToken() != null) {
			list.setToken(listValue.getToken());
		}
		if (user != null) {

			userv.add(user);
		}
		if (task != null) {
			taskv.add(task);
		}
		TodoList listtosave = new TodoList(list.getTitle(), taskv, userv);
		TodoList listU = listRepository.save(listtosave);
		return ResponseEntity.ok(listU);
	}

	/* Update partial values of a list and add/replace task and users */
	@Override
	public ResponseEntity<TodoList> partialUpdate(long id, TodoList listValue) {

		/* get the list */
		TodoList list = listRepository.getBylistId(id);

		/* if user update this value it will be committe the repository */
		if (list == null) {
			ResponseEntity.notFound().build();
		}

		if (listValue.getToken() != null) {
			list.setToken(listValue.getToken());
		}
		TodoList listU = listRepository.save(list);
		return ResponseEntity.ok(listU);
	}

	/* handle the share token response */
	@Override
	public ResponseEntity<TodoList> getByToken(String token) {
		TodoList list = listRepository.findByToken(token);
		if (list == null) {
			return ResponseEntity.notFound().build();
		}

		return partialUpdate(list.getListId(), list);
	}

	/* list share inivitation link */
	@Override
	public ResponseEntity<String> Sharelist(String email, Long id, HttpServletRequest request) {

		TodoList list = listRepository.getBylistId(id);
		User user = userRepository.getByEmail(email);

		Set<User> userValue = new HashSet<>();

		// string UUID base random token for confirmation link
		list.setToken(UUID.randomUUID().toString());
		if (user != null) {
			userValue.add(user);
			list.setUsers(userValue);
		}

		 partialUpdate(id, list);

		String appUrl = request.getScheme() + "://" + request.getServerName();

		SimpleMailMessage sharelink = new SimpleMailMessage();
		sharelink.setTo(email);
		sharelink.setSubject("Sharing Confirmation");
		sharelink.setText("Accept the share invitation for the Todolist task, Please click the link below:\n" + appUrl
				+ ":8080/api/v1/list/{token}?token=" + list.getToken());
		sharelink.setFrom("wmoc0074@gmail.com");

		emailService.sendEmail(sharelink);

		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<TodoList>> findByUserId(long userId) {

		User user = userRepository.getByuserId(userId);

		List<TodoList> lists = listRepository.findAll();
		List<TodoList> listByuser = new ArrayList<TodoList>();

		for (TodoList list : lists) {
			if (list.getUsers().contains(user)) {
				listByuser.add(list);
			}
		}
		if (lists == null) {
			ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(listByuser);
	}

}
