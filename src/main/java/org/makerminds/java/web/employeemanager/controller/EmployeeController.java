package org.makerminds.java.web.employeemanager.controller;

import jakarta.validation.Valid;

import java.security.Principal;

import org.makerminds.java.web.employeemanager.entity.Employee;
import org.makerminds.java.web.employeemanager.service.EmployeeService;
import org.makerminds.java.web.employeemanager.service.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/employees")
@CrossOrigin
public class EmployeeController {

	private final EmployeeService employeeService;
	private final MapValidationErrorService mapValidationErrorService;

	public EmployeeController(EmployeeService employeeService, MapValidationErrorService mapValidationErrorService) {
		this.employeeService = employeeService;
		this.mapValidationErrorService = mapValidationErrorService;
	}

	@GetMapping(path = "/all")
	public Iterable<Employee> getEmployees(Principal principal) {
		return employeeService.getAllEmployees(principal.getName());
	}

	@DeleteMapping(path = "delete/{id}")
	public void deleteEmployee(@PathVariable Long id, Principal principal) {
		employeeService.deleteEmployee(id, principal.getName());

	}

	@PostMapping("/{dep_id}")
	public ResponseEntity<?> addEmployeeToTheBacklog(@Valid @RequestBody Employee employee, BindingResult result,
			@PathVariable Long dep_id, Principal principal) {

		ResponseEntity<?> erroMap = mapValidationErrorService.mapValidationService(result);
		if (erroMap != null)
			return erroMap;
		Employee emp = employeeService.createOrUpdateEmployee(employee, dep_id, principal.getName());

		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}

	@GetMapping("/list/{dep_id}")
	public Iterable<Employee> getEmployeeList(@PathVariable Long dep_id, Principal principal) {
		return employeeService.findByDepartmentId(dep_id, principal.getName());
	}

	@GetMapping("/{backlog_id}/{employee_id}")
	public Employee getEmployee(@PathVariable Long backlog_id, @PathVariable Long employee_id, Principal principal) {
		return employeeService.findById(backlog_id, employee_id,  principal.getName());
	}

	
}