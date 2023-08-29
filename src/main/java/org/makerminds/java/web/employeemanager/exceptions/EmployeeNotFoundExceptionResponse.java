package org.makerminds.java.web.employeemanager.exceptions;
//Create EmployeeNotFoundExceptionResponse class
public class EmployeeNotFoundExceptionResponse {

    private String EmployeeNotFound;

    public EmployeeNotFoundExceptionResponse(String employeeNotFound) {
    	EmployeeNotFound = employeeNotFound;
    }

    public String getEmployeeNotFound() {
        return EmployeeNotFound;
    }

    public void setEmployeeNotFound(String employeeNotFound) {
    	EmployeeNotFound = employeeNotFound;
    }
}