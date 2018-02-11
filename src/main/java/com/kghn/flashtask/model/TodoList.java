package com.kghn.flashtask.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kghn.flashtask.model.Task;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created", "modified" }, allowGetters = true)
@Table(name = "list", catalog = "flashtask")
public class TodoList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "list_id")
	private long listId;
	private String title;
	private boolean sharable;
	private String token;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modified;

	/* create a many to one relationship between users and lists */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "list_task", joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "list_id"), inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "task_id"))
	private Set<Task> tasks = new HashSet<>();

	/*
	 * create a many to one relationship between users and lists and user Json
	 * properties to break the inifinity loop
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "list_user", joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "list_id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
	private Set<User> users = new HashSet<>();

	public TodoList() {
		super();
	}

	public TodoList(String title) {
		this(title, null, null);
	}

	public TodoList(String title, Set<Task> tasks, Set<User> users) {
		super();
		this.title = title;
		this.tasks = tasks;
		this.users = users;
	}

	public TodoList(String title, boolean sharable, String token, Date created, Date modified) {
		super();
		this.title = title;
		this.sharable = sharable;
		this.token = token;
		this.created = created;
		this.modified = modified;

	}

	public long getListId() {
		return listId;
	}

	public void setListId(long listId) {
		this.listId = listId;
	}

	/*
	 * public void setTask(Set<Task> tasks) { this.tasks = tasks; }
	 */

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isSharable() {
		return sharable;
	}

	public void setSharable(boolean sharable) {
		this.sharable = sharable;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}
