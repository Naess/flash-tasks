package com.kghn.flashtask.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "task", catalog = "flashtask2")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "taskid")
	private Long taskId;
	private String title;
	private String discription;
	private String estimate;
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list", nullable = false)
	private TodoList list;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modified;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "modified_by")
	private Long modifiedBy;
	
	
	
	public Task() {
		super();
	
	}

	public Task(String title, String discription, String estimate, String status, TodoList list,
			Integer userId, Date created, Date modified, Long createdBy, Long modifiedBy) {
		super();
		this.title = title;
		this.discription = discription;
		this.estimate = estimate;
		this.status = status;
		this.list = list;
		//this.userId = userId;
		this.created = created;
		this.modified = modified;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public Long getId() {
		return taskId;
	}

	public void setId(Long id) {
		this.taskId = id;
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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
