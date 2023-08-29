package org.makerminds.java.web.employeemanager.repository;

import java.util.Optional;

import org.makerminds.java.web.employeemanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

	Task findByTaskSequence(String taskSequence);
	Optional<Task> findById(Long Id);
}
