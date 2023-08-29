package org.makerminds.java.web.employeemanager.repository;

import java.util.List;

import org.makerminds.java.web.employeemanager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findById(int Id);
    List<Employee> findByDepartmentBacklogId(Long backlogId);
	Employee findByEmployeeIdentifier(String employeeIdentifier);
}