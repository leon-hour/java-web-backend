package org.makerminds.java.web.employeemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String taskSequence;
	@NotBlank (message = " Task summary is required")
	private String summary;
	@NotBlank (message = " Acceptance criterias are required.")
	private String acceptanceCriteria;
	private String status = "INPUT QUEUE";
	private int priority = 0;
	//ManyToOne with employeeBacklog
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_blog_id", updatable = false, nullable = false)
	@JsonIgnore
	private EmployeeBacklog employeeBacklog;
	
	public EmployeeBacklog getEmployeeBacklog() {
		return employeeBacklog;
	}
	public void setEmployeeBacklog(EmployeeBacklog employeeBacklog) {
		this.employeeBacklog = employeeBacklog;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Task() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeSequence() {
		return taskSequence;
	}
	public void setEmployeeSequence(String taskSequence) {
		this.taskSequence = taskSequence;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}
	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}
	
}
