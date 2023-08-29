package org.makerminds.java.web.employeemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@NotBlank(message = "Name in required.")
	private String name;

	@NotBlank(message = "Address is required.")
	@Column(name = "address", nullable = false)
	private String address;

	@NotBlank(message = "Email in required.")
	@Email(message = "Email is not well formatted.")
	@Column(name = "email", nullable = false)
	private String email;

	@NotBlank(message = "Phone number in required.")
	@Size(min = 12, max = 12, message = "Invalid phone number. Phone Numer format +383########.")
	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "empIdentifier", nullable = false, updatable = false)
	private String employeeIdentifier;

	// ManyToOne
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_blog_id", updatable = false, nullable = false)
	private DepartmentBacklog departmentBacklog;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonIgnore
	private EmployeeBacklog employeeBacklog;

	public Employee() {

	}

	public Employee(Long id, String name, String address, String email, String phone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmployeeIdentifier() {
		return employeeIdentifier;
	}

	public void setEmployeeIdentifier(String employeeIdentifier) {
		this.employeeIdentifier = employeeIdentifier;
	}

	public EmployeeBacklog getEmployeeBacklog() {
		return employeeBacklog;
	}

	public void setEmployeeBacklog(EmployeeBacklog employeeBacklog) {
		this.employeeBacklog = employeeBacklog;
	}

	public DepartmentBacklog getDepartmentBacklog() {
		return departmentBacklog;
	}

	public void setDepartmentBacklog(DepartmentBacklog departmentBacklog) {
		this.departmentBacklog = departmentBacklog;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phone="
				+ phone + "]";
	}

}
