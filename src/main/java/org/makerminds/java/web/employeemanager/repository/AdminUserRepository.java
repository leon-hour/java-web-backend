package org.makerminds.java.web.employeemanager.repository;

import org.makerminds.java.web.employeemanager.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long>{

	AdminUser findByEmail(String email);
	AdminUser getById(Long id);
	void save(UserDetails user);
	AdminUser findByName(String name);
	
}
