package org.makerminds.java.web.employeemanager.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.makerminds.java.web.employeemanager.entity.Department;
import org.makerminds.java.web.employeemanager.entity.DepartmentBacklog;
import org.makerminds.java.web.employeemanager.entity.Employee;
import org.makerminds.java.web.employeemanager.entity.EmployeeBacklog;
import org.makerminds.java.web.employeemanager.exceptions.BacklogIdException;
import org.makerminds.java.web.employeemanager.exceptions.DepartmentIdExceptions;
import org.makerminds.java.web.employeemanager.exceptions.EmployeeIdExceptions;
import org.makerminds.java.web.employeemanager.repository.DepartmentBacklogRepository;
import org.makerminds.java.web.employeemanager.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentBacklogRepository departmentBacklogRepository;
	private final DepartmentService departmentService;

	public EmployeeService(EmployeeRepository employeeRepository,
			DepartmentBacklogRepository departmentBacklogRepository,
			DepartmentService departmentService) {
		this.employeeRepository = employeeRepository;
		this.departmentBacklogRepository = departmentBacklogRepository;
		this.departmentService = departmentService;
	}

	public Employee createOrUpdateEmployee(Employee employee, Long dep_id, String email) {
		Department department = departmentService.findById(dep_id, email);
		DepartmentBacklog departmentBacklog;
		try {
			Long backlogId = department.getBacklog().getId();
			departmentBacklog = departmentBacklogRepository.findById(backlogId).get();
		} catch (Exception e) {
			throw new BacklogIdException("No DepartmentBacklog was found for the given id.");
		}
		if (employee.getId() == null) {
			employee.setDepartmentBacklog(departmentBacklog);
			Integer backlogSequence = departmentBacklog.getEmpSequence();
			backlogSequence++;
			departmentBacklog.setEmpSequence(backlogSequence);
			employee.setEmployeeIdentifier("dep" + departmentBacklog.getId() + "-emp" + backlogSequence);
			EmployeeBacklog employeeBacklog = new EmployeeBacklog();
			employeeBacklog.setEmployee(employee);
			employee.setEmployeeBacklog(employeeBacklog);
		} else {
			try {
				Employee employee1 = null;
				Iterator<Employee> employeeList = departmentBacklog.getEmployeeList().iterator();
				while (employeeList.hasNext()) {
					Employee emp = employeeList.next();
					if (emp.getId() == employee.getId()) {
						employee1 = emp;
					}
				}
				employee.setEmployeeIdentifier(employee1.getEmployeeIdentifier());
				employee.setDepartmentBacklog(departmentBacklog);
				employee.setEmployeeBacklog(employee1.getEmployeeBacklog());
			} catch (Exception ex) {
				throw new EmployeeIdExceptions("In the department with ID" + dep_id + ", no employee with ID "
						+ employee.getId() + " was found");
			}
		}
		return employeeRepository.save(employee);
	}

	

	public ResponseEntity<String> findByName(String name) {
		List<Employee> employee = employeeRepository.findByName(name);
		if (employee.size() != 0) {
			String message = "Employee with Name:" + name + " has been found.";
			return ResponseEntity.ok().body(message);
		} else {
			String message = "Employee with Name: " + name + " not found.";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}

	public List<Employee> getAllEmployees(String email) {
		List<Department> departmentList = departmentService.getAllDepartments(email);
		List<Employee> employeeList = new ArrayList<>();
		for (Department department : departmentList) {
			Iterable<Employee> employeeListForDepartment = findByDepartmentId(department.getId(), email);
			for (Employee employee : employeeListForDepartment) {
				employeeList.add(employee);
			}
		}
		return employeeList;
	}

	public void deleteEmployee(Long id, String email) {
		try {
			Employee employee = employeeRepository.findById(id).get();
			String emailOdThe = employee.getDepartmentBacklog().getDepartment().getAdmin().getEmail();
			if (!email.equals(emailOdThe)) {
				throw new EmployeeIdExceptions(
						"Cannot delete the employee with ID '" + id + "'. This employee does not exist");
			}
			employeeRepository.delete(employee);
		} catch (Exception e) {
			throw new EmployeeIdExceptions(
					"Cannot delete the employee with ID '" + id + "'. This employee does not exist");
		}
	}

	public Iterable<Employee> findByDepartmentId(Long dep_id, String email) {
		DepartmentBacklog departmentBacklog = departmentService.findById(dep_id, email).getBacklog();
		return departmentBacklog.getEmployeeList();
	}

	public Employee findById(Long dep_id, Long employee_id, String email) {
		Long backLogId = departmentService.findById(dep_id, email).getBacklog().getId();
		Optional<DepartmentBacklog> departmentBacklog = departmentBacklogRepository.findById(backLogId);
		if (departmentBacklog.isEmpty()) {
			throw new DepartmentIdExceptions("No DepartmentBacklog was found for the given id.");
		}
		Optional<Employee> employee = employeeRepository.findById(employee_id);
		if (employee.isEmpty()) {
			throw new EmployeeIdExceptions("Employee with ID " + employee_id + " not found.");
		}
		if (!employee.get().getDepartmentBacklog().equals(departmentBacklog.get())) {
			throw new EmployeeIdExceptions(
					"Employee with id " + employee_id + " does not exist in the department with  id" + dep_id);
		}
		return employee.get();
	}
}