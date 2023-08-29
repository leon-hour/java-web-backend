package org.makerminds.java.web.employeemanager.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.makerminds.java.web.employeemanager.entity.AdminUser;
import org.makerminds.java.web.employeemanager.entity.Employee;
import org.makerminds.java.web.employeemanager.entity.EmployeeBacklog;
import org.makerminds.java.web.employeemanager.entity.Task;
import org.makerminds.java.web.employeemanager.exceptions.EmployeeIdExceptions;
import org.makerminds.java.web.employeemanager.repository.EmployeeBacklogRepository;
import org.makerminds.java.web.employeemanager.repository.EmployeeRepository;
import org.makerminds.java.web.employeemanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	private EmployeeBacklogRepository employeeBacklogRepository;
	private EmployeeRepository employeeRepository;
	private EmployeeService employeeService;
	private TaskRepository taskRepository;
	
	public TaskService(EmployeeBacklogRepository employeeBacklogRepository, EmployeeRepository employeeRepository,
			EmployeeService employeeService, TaskRepository taskRepository) {
		this.employeeBacklogRepository = employeeBacklogRepository;
		this.employeeRepository = employeeRepository;
		this.employeeService = employeeService;
		this.taskRepository = taskRepository;
	}

	public Task createOrUpdateTask(@Valid Task task, Long department_id, Long employee_id, String email) {
		EmployeeBacklog employeeBacklog;
		try {
			long employeeBacklog_id = employeeService.findById(department_id, employee_id, email).getEmployeeBacklog().getId();
			employeeBacklog = employeeBacklogRepository.findById(employeeBacklog_id).get();
		} catch (Exception e) {
			throw new EmployeeIdExceptions("No Employee was found for the given id.");
		}
		if (task.getId() == null) {
			task.setEmployeeBacklog(employeeBacklog);
			Integer backlogSequence = employeeBacklog.getTaskSequence();
			backlogSequence++;
			employeeBacklog.setTaskSequence(backlogSequence);
			task.setEmployeeSequence("emp" + employee_id + "-task" + backlogSequence);
		} else {
			try {
				Task existingTask = null;
				Iterator<Task> taskList = employeeBacklog.getTaskList().iterator();
				while (taskList.hasNext()) {
					Task emp = taskList.next();
					if (emp.getId() == task.getId()) {
						existingTask = emp;
					}
				}
				task.setEmployeeBacklog(employeeBacklog);
				task.setEmployeeSequence(existingTask.getEmployeeSequence());
			} catch (Exception ex) {
				throw new EmployeeIdExceptions("In the employee with ID" + employee_id + ", no Task with ID "
						+ task.getId() + " was found");
			}
			if(task.getPriority() == 0) {
				task.setPriority(3);	
			}
			if(task.getStatus().equals("")) {
				task.setStatus("INPUT QUEUE");	
			}
		}
		return taskRepository.save(task);
	}

	public Iterable<Task> findByEmployeeBacklogId(Long dep_id, Long emp_id, String email) {
		return employeeService.findById(dep_id, emp_id, email).getEmployeeBacklog().getTaskList();
	}

	public Task findByDepNameEmployeeIdentifierTaskSequence(long dep, long emp,
			long taskid, String email) {
		Employee employee = null;
		employee = employeeService.findById(dep, emp, email);
		Task task = taskRepository.findById(taskid).get();
		if (!task.getEmployeeBacklog().equals(employee.getEmployeeBacklog())) {
			throw new EmployeeIdExceptions("In the employee with the id" + emp
					+ ", no Task with ID " + task.getId() + " was found");
		}
		return task;
	}

	public Task findById(Long id, String email) {
		//return taskRepository.findById(id).orElse(null);
		Optional<Task> task = taskRepository.findById(id);

		if (task.isEmpty()) {
			throw new EmployeeIdExceptions(
					"Cannot delete the task with ID '" + id + "'. This task does not exist");
		}
		else {
			AdminUser adminUser = task.get().getEmployeeBacklog().getEmployee().getDepartmentBacklog().getDepartment().getAdmin();
			if(adminUser.getEmail().equals(email)) {
				return task.get();
			}
			else
			{
				throw new EmployeeIdExceptions(
						"Cannot delete the task with ID '" + id + "'. This task does not exist");
			}
		}
	}

	public void deleteTask(Long id, String email) {
		Task task = findById(id, email);

				if(task != null) {
				taskRepository.delete(task);
			}
			else
			{
				throw new EmployeeIdExceptions(
						"Cannot delete the task with ID '" + id + "'. This task does not exist");
			}
		}
	
	public List<Task> getAllTasks(String email) {
		 List<Employee> employeeList = employeeService.getAllEmployees(email);
		 List<Task> taskList = new ArrayList<>();
		 for(Employee employee : employeeList) {
			 List<Task>employeeTaskList = employee.getEmployeeBacklog().getTaskList();
			 for(Task task : employeeTaskList) {
				 taskList.add(task);
			 }
		 }
		 return taskList;
	}
}
