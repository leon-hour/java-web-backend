package org.makerminds.java.web.employeemanager.service;

import org.makerminds.java.web.employeemanager.repository.AdminUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CostumUserDetailsService implements UserDetailsService{

	public CostumUserDetailsService(AdminUserRepository adminUserRepository) {
		this.adminUserRepository = adminUserRepository;
	}

	private AdminUserRepository adminUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(adminUserRepository.findByEmail(email).getName());
		
		if(adminUserRepository.findByEmail(email) != null)
		return adminUserRepository.findByEmail(email);
		else
		{
			throw new UsernameNotFoundException("dsadasd");
		}
	}

}
