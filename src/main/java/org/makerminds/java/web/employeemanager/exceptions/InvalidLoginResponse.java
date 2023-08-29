package org.makerminds.java.web.employeemanager.exceptions;

public class InvalidLoginResponse {
    private String message;

    public InvalidLoginResponse() {
        this.setMessage("Authentication Is Required. Invalid username or password");
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}