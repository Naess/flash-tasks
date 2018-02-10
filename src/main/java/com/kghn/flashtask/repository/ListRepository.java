/* CRUD Operations */

package com.kghn.flashtask.repository;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import com.kghn.flashtask.model.TodoList;

public interface ListRepository extends Repository<TodoList, Long> {

	void delete(TodoList deleted);

	List<TodoList> findAll();

	@Async
	TodoList getById(long id);

	TodoList save(TodoList list);

	ResponseEntity<TodoList> findById(long id);

	@Async
	@Query(value = "SELECT t.title FROM Task t where LOWER(t.title) LIKE LOWER(:title)")
	Future<Stream<TodoList>> findByTitle(@Param("title") String title);

}
