package org.makerminds.java.web.employeemanager.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;

import org.makerminds.java.web.employeemanager.entity.Department;
import org.makerminds.java.web.employeemanager.exceptions.DepartmentIdExceptions;
import org.makerminds.java.web.employeemanager.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/departments")
@CrossOrigin
public class DepartmentController {
	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping(path = "/all")
	public Iterable<Department> getDepartments(Principal principal) {
		return departmentService.getAllDepartments(principal.getName());
	}

	@PostMapping()
	public ResponseEntity<?> createDepartmnet(@Valid @RequestBody Department department, 
			BindingResult results,
			Principal principal) {
		if(results.hasErrors()){
			Map<String, String> errorMap = new HashMap<String, String>();
			for(FieldError error : results.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		else
		{
			System.out.println(principal.getName());
			return ResponseEntity.ok(departmentService.createOrUpdateDepartment(department, principal.getName()));
		}
	}

	@DeleteMapping(path = "delete/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Long id, Principal principal) {
		return departmentService.delete(id, principal);
	}

	@GetMapping(path = "/{name}")
	public ResponseEntity<String> getDepartmentWithName(@PathVariable String name) {
		return departmentService.findByName(name);
	}
	
	@GetMapping(path = "id/{id}")
	public Department getDepartmentById(@PathVariable Long id, Principal principal) {
		Department department =  departmentService.findById(id, principal.getName());
		if(department == null) {
			throw new DepartmentIdExceptions("The department with id:" + id + " does not exist");
		}
		return department;
	}
}
