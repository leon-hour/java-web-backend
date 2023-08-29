package org.makerminds.java.web.employeemanager.exceptions;
// Create DepartmentNotFoundExceptionResponse class
public class DepartmentNotFoundExceptionResponse {

    private String DepartmentNotFound;

    public DepartmentNotFoundExceptionResponse(String departmentNotFound) {
    	DepartmentNotFound = departmentNotFound;
    }

    public String getDepartmentNotFound() {
        return DepartmentNotFound;
    }

    public void setDepartmenttNotFound(String departmentNotFound) {
        DepartmentNotFound = departmentNotFound;
    }
}