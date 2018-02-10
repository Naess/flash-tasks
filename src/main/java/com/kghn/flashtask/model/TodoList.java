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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import  com.kghn.flashtask.model.Task;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created", "modified" }, allowGetters = true)
@Table(name = "list", catalog = "flashtask")
public class TodoList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "listId")
	private long id;
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

	@OneToMany(mappedBy = "list")
	private Set<Task> task = new HashSet<Task>();


	@ManyToMany(cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinTable(name = "list_user", joinColumns = @JoinColumn(name = "listId", referencedColumnName = "listId"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"))
	private Set<User> users = new HashSet<User>();

	public TodoList() {
		super();
	}

	public TodoList(String title, String createdBy, String modifiedBy) {
		this();
		this.setTitle(title);
	}

	public TodoList(String title, boolean sharable, String token, Set<Task> taskId, Date created, Date modified) {
		super();
		this.title = title;
		this.sharable = sharable;
		this.token = token;
		this.created = created;
		this.modified = modified;

	}

	public long getId() {
		return id;
	}

	public void setId(long listid) {
		this.id = listid;
	}

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

	public Set<Task> getTasks() {
		return task;
	}

	public void setTasks(Set<Task> tasks) {
		this.task = tasks;
	}

}
