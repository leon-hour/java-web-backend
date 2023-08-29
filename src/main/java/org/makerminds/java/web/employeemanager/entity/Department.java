package org.makerminds.java.web.employeemanager.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "departments")

public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	@NotBlank(message = "The department name is required.")
	private String name;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "department")
	@JsonIgnore
	private DepartmentBacklog backlog;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private AdminUser admin;
    public DepartmentBacklog getBacklog() {
		return backlog;
	}

	public void setBacklog(DepartmentBacklog backlog) {
		this.backlog = backlog;
	}

	public AdminUser getAdmin() {
		return admin;
	}

	public void setAdmin(AdminUser admin) {
		this.admin = admin;
	}

	private String adminName;
    
	public Department() {
	}

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

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}
