package org.makerminds.java.web.employeemanager.service;

import java.security.Principal;
import java.util.List;

import org.makerminds.java.web.employeemanager.entity.DepartmentBacklog;
import org.makerminds.java.web.employeemanager.exceptions.DepartmentIdExceptions;
import org.makerminds.java.web.employeemanager.entity.AdminUser;
import org.makerminds.java.web.employeemanager.entity.Department;
import org.makerminds.java.web.employeemanager.repository.AdminUserRepository;
import org.makerminds.java.web.employeemanager.repository.DepartmentBacklogRepository;
import org.makerminds.java.web.employeemanager.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.conditional.IfAction;

@Service
public class DepartmentService {

	private DepartmentRepository departmentRepository;
	private AdminUserRepository adminUserRwpository;
	private DepartmentBacklogRepository departmentBackLogRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository,
			DepartmentBacklogRepository departmentBackLogRepository,
			AdminUserRepository adminUserRepository) {
		this.departmentRepository = departmentRepository;
		this.departmentBackLogRepository = departmentBackLogRepository;
		this.adminUserRwpository = adminUserRepository;
	}

	public Department createOrUpdateDepartment(Department department, String email) {
		if (department.getId() == null) {
			AdminUser adminUser = adminUserRwpository.findByEmail(email);
			department.setAdmin(adminUser);
			department.setAdminName(adminUser.getName());
			DepartmentBacklog backlog = new DepartmentBacklog();
			department.setBacklog(backlog);
			backlog.setDepartment(department);
			backlog.setDepartmentName(department.getName());
		} else {
			AdminUser admin = departmentRepository.findById(department.getId()).get().getAdmin();
			if(admin.equals(adminUserRwpository.findByEmail(email)))
			{
			try {
				department.setBacklog(departmentBackLogRepository.findById(department.getId()).get());
				department.getBacklog().setDepartmentName(department.getName());
			} catch (Exception e) {
				throw new DepartmentIdExceptions("Does not exsict any department for the given id.");
			}
			}
			else
			{
				throw new DepartmentIdExceptions("In your account does not exsict any department for the given id.");
			}
		}
		return departmentRepository.save(department);

	}

	public ResponseEntity<String> delete(Long id, Principal principal) {
		Department department = findById(id, principal.getName());
		if (department != null) {
			departmentRepository.deleteById(department.getId());
			String message = "Department with ID " + id + " has been deleted.";
			return ResponseEntity.ok().body(message);
		} else {
			String message = "Department with ID " + id + " not found.";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}

	public Department findById(Long id, String email) {
		Department department = departmentRepository.findById(id).orElse(null);
		AdminUser user = adminUserRwpository.findByEmail(email);
		if (department != null) {
			if(department.getAdmin().equals(user))
			return department;
			else {
				String message = "Department with id: " + id + " not found.";
				throw new  DepartmentIdExceptions(message);
			}
		} 
		else {
			String message = "Department with id: " + id + " not found.";
			throw new  DepartmentIdExceptions(message);
		}
	}

	public ResponseEntity<String> findByName(String name) {
		List<Department> department = departmentRepository.findByName(name);
		if (department.size() != 0) {
			String message = "Department with Name:" + name + " has been found.";
			return ResponseEntity.ok().body(message);
		} else {
			String message = "Department with Name: " + name + " not found.";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}

	public List<Department> getAllDepartments(String email) {
		AdminUser user = adminUserRwpository.findByEmail(email);
		return departmentRepository.findByAdminId(user.getId());
	}
}
