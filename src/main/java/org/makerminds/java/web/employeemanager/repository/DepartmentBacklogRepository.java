package org.makerminds.java.web.employeemanager.repository;

import java.util.Optional;

import org.makerminds.java.web.employeemanager.entity.DepartmentBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentBacklogRepository extends JpaRepository<DepartmentBacklog, Long> {

	DepartmentBacklog findByDepartmentName(String departmentName);
	DepartmentBacklog findByDepartment(Long dep);
	Optional<DepartmentBacklog> findById(Long id);
}
