package org.makerminds.java.web.employeemanager.repository;

import java.util.List;
import java.util.Optional;

import org.makerminds.java.web.employeemanager.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	   List<Department> findByName(String name);
	   Optional<Department> findById(Long Id);
	   List<Department> findByAdminId(Long adminId);
}
