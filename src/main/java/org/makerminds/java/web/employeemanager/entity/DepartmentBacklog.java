package org.makerminds.java.web.employeemanager.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DepartmentBacklog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer EmpSequence = 0;
	private String departmentName;

	// OneToOne
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Department_id", nullable = false)
	@JsonIgnore
	private Department department;
	// OneToMany
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy ="departmentBacklog",orphanRemoval = true) 
	@JsonIgnore
	private List<Employee> employeeList = new ArrayList<>();

	public DepartmentBacklog() {

	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEmpSequence() {
		return EmpSequence;
	}

	public void setEmpSequence(Integer empSequence) {
		EmpSequence = empSequence;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
}
