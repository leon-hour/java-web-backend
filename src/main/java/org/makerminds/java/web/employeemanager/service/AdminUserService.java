package org.makerminds.java.web.employeemanager.service;

import org.makerminds.java.web.employeemanager.entity.AdminUser;
import org.makerminds.java.web.employeemanager.exceptions.UserAlreadyExistException;
import org.makerminds.java.web.employeemanager.repository.AdminUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

	private final AdminUserRepository adminUserRepository;
	
	private final PasswordEncoder bCryptPasswordEncoder;

	public AdminUserService( AdminUserRepository adminUserRepository, PasswordEncoder bCryptPasswordEncoder) {
		this.adminUserRepository = adminUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public AdminUser saveUser(AdminUser newAdminUser) {
		
	try {
			newAdminUser.setPassword(bCryptPasswordEncoder.encode(newAdminUser.getPassword()));
			newAdminUser.setEmail(newAdminUser.getEmail());
			newAdminUser.setRole(newAdminUser.getRole());
			return adminUserRepository.save(newAdminUser);	
	}
	catch(Exception e) {
		throw new UserAlreadyExistException("This email is already in use.");
	}
		}
	public AdminUser getUserByEmail(String email) {
		return adminUserRepository.findByEmail(email);
		
	}
}
