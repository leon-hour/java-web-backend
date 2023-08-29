package org.makerminds.java.web.employeemanager.exceptions;
//Create backlog id exception class
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BacklogIdException extends RuntimeException {
	public BacklogIdException(String message) {
		super(message);
	}
}