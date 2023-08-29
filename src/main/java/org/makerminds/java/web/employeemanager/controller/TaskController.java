package org.makerminds.java.web.employeemanager.controller;

import jakarta.validation.Valid;

import java.security.Principal;

import org.makerminds.java.web.employeemanager.entity.Task;
import org.makerminds.java.web.employeemanager.service.MapValidationErrorService;
import org.makerminds.java.web.employeemanager.service.TaskService;
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
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

	private final TaskService taskService;
	private final MapValidationErrorService mapValidationErrorService;
	
	public TaskController(TaskService taskService, MapValidationErrorService mapValidationErrorService) {
		this.taskService = taskService;
		this.mapValidationErrorService = mapValidationErrorService;
	}

	@PostMapping("{department_id}/{employee_id}")
	public ResponseEntity<?> addTaskTotheBacklog(@Valid @RequestBody Task task, 
			BindingResult result,
			@PathVariable Long department_id,
			@PathVariable Long employee_id,
			Principal principal) {
		//Create MapValidationErrorService
		//display the errors if we are trying to create an invalid task.
		ResponseEntity<?> erroMap = mapValidationErrorService.mapValidationService(result);
		if (erroMap != null)
			return erroMap;

		Task newTask =taskService.createOrUpdateTask(task, department_id, employee_id, principal.getName());

		return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/{department_id}/{employee_id}")
	public Iterable<Task> getTaskList(@PathVariable Long department_id, @PathVariable Long employee_id, Principal principal ){
		return taskService.findByEmployeeBacklogId(department_id, employee_id, principal.getName());
	}
	
	@GetMapping("/{dep}/{emp}/{task}")
	public Task getTask(@PathVariable long dep,@PathVariable long emp,@PathVariable long task, Principal principal) {
	return taskService.findByDepNameEmployeeIdentifierTaskSequence(dep,emp,task, principal.getName());
	}
	
	@DeleteMapping(path = "delete/{id}")
	public void deleteTask(@PathVariable Long id, Principal principal) {
		taskService.deleteTask(id, principal.getName());
	}
	
	@GetMapping(path = "/all")
	public Iterable<Task> getTask(Principal principal) {
		return taskService.getAllTasks(principal.getName());
	}
}