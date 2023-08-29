package org.makerminds.java.web.employeemanager.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmployeeBacklog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer TaskSequence = 0;
	//OneToOne 
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_id", nullable = false)
	@JsonIgnore
	private Employee employee;
	// OneToMany
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy ="employeeBacklog",orphanRemoval = true) 
	@JsonIgnore
	private List<Task> taskList = new ArrayList<>();
	
	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public EmployeeBacklog() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTaskSequence() {
		return TaskSequence;
	}

	public void setTaskSequence(Integer taskSequence) {
		TaskSequence = taskSequence;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
