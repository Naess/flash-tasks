/* CRUD Operations */

package com.kghn.flashtask.repository;

import java.util.List;
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
	TodoList getBylistId(long id);

	TodoList save(TodoList list);

	ResponseEntity<TodoList> findBylistId(long id);
	/* Query using normal query title  */
	@Async
	@Query(value = "SELECT * FROM list t where LOWER(t.title) LIKE LOWER(:title)", nativeQuery=true)
	List<TodoList> findByTitle(@Param("title") String title);
	/* Query using normal query  token*/
	@Async
	@Query(value = "SELECT * FROM list t where LOWER(t.token) LIKE LOWER(:token)", nativeQuery=true)
	TodoList findByToken(@Param("token") String token);

}
