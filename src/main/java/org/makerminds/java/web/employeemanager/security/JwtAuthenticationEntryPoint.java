package org.makerminds.java.web.employeemanager.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.makerminds.java.web.employeemanager.exceptions.InvalidLoginResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class JwtAuthenticationEntryPoint implements 
AuthenticationEntryPoint{

	
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException) 
					throws IOException, ServletException {
			
		InvalidLoginResponse invaidLoginResponse = new InvalidLoginResponse();
		String jsonLoginResponse = new Gson().toJson(invaidLoginResponse);
		
		response.setContentType("application/json");
		response.setStatus(400);
		response.getWriter().print(jsonLoginResponse);
	
	
	}

	
	
}
