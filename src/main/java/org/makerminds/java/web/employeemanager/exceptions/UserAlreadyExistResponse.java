package org.makerminds.java.web.employeemanager.exceptions;

public class UserAlreadyExistResponse {
	private String email;

	public UserAlreadyExistResponse(String email) {
		super();
		this.setEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
