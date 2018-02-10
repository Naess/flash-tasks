package com.kghn.flashtask.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import  com.kghn.flashtask.model.TodoList;


@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created", "modified" }, allowGetters = true)
@Table(name = "task", catalog = "flashtask")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "taskId")
	private long id;
	private String title;
	private String discription;
	private String estimate;
	private String status;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modified;

	/*@ManyToMany
	@JoinTable(name = "list_task", joinColumns = @JoinColumn(name = "taskId", referencedColumnName = "taskId"), 
	inverseJoinColumns = @JoinColumn(name = "listId", referencedColumnName = "listId"))
	private Set<TodoList> lists = new HashSet<TodoList>();*/
	

	@ManyToOne
	@JoinColumn (name="listId")
	@JsonBackReference
	private TodoList list;

	// default non arg constructor
	public Task() {

	}


	public Task(String title, String estimate, String status, Date created) {
		super();
		this.title = title;
		this.estimate = estimate;
		this.status = status;
		this.created = created;
	}


	public long getTaskId() {
		return id;
	}

	public void setTaskId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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


	public TodoList getList() {
		return list;
	}

	public void setList(TodoList list) {
		this.list = list;
	}

}
