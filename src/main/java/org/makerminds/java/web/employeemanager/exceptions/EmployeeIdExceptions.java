package org.makerminds.java.web.employeemanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeIdExceptions extends RuntimeException {

	public EmployeeIdExceptions(String message) {
	        super(message);
	    }
}