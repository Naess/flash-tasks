package com.kghn.flashtask.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created", "modified" }, allowGetters = true)
@Table(name = "list", catalog = "flashtask2")
public class TodoList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "listid")
	private Long listId;

	private String title;

	private Long userId;

	private boolean sharable;

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "list")
	private Set<User> buddyId = new HashSet<User>();

	private String token;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "list")
	private Set<Task> taskId = new HashSet<Task>();
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modified;
	
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "modified_by")
	private String modifiedBy;
	
	
	public TodoList() {
		super();
	}
	

	public TodoList(String title, Long userId, String createdBy, String modifiedBy) {
		this();
		this.setTitle(title);
		this.setUserId(userId);
		this.setCreatedBy(createdBy);
		this.setModifiedBy(modifiedBy);
	}
	public TodoList(Set<User> buddyId, Set<Task> taskId) {
		this();
		this.setTitle(title);
		this.setUserId(userId);
		this.setCreatedBy(createdBy);
		this.setModifiedBy(modifiedBy);
	}

	public TodoList(String title, Long userId, boolean sharable, Set<User> buddyId, String token, Set<Task> taskId,
			Date created, Date modified, String createdBy, String modifiedBy) {
		super();
		this.title = title;
		this.userId = userId;
		this.sharable = sharable;
		this.buddyId = buddyId;
		this.token = token;
		this.taskId = taskId;
		this.created = created;
		this.modified = modified;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}


	public Long getId() {
		return listId;
	}

	public void setId(Long listid) {
		this.listId = listid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isSharable() {
		return sharable;
	}

	public void setSharable(boolean sharable) {
		this.sharable = sharable;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "list_user", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "id") })
	public Set<User> getBuddyId() {
		return buddyId;
	}

	public void setBuddyId(Set<User> buddyId) {
		this.buddyId = buddyId;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Set<Task> getTaskId() {
		return taskId;
	}

	public void setTaskId(Set<Task> taskId) {
		this.taskId = taskId;
	}
	
}
