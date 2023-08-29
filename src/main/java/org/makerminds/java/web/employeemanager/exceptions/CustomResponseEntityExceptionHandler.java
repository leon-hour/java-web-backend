package org.makerminds.java.web.employeemanager.exceptions;
// Create CustomResponseEntityExceptionHandler
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler
    public final ResponseEntity<?> handleProjectIdException(DepartmentIdExceptions ex, WebRequest request){
		DepartmentNotFoundExceptionResponse exceptionResponse = new DepartmentNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleProjectNotFoundException(EmployeeIdExceptions ex, WebRequest request){
    	EmployeeNotFoundExceptionResponse exceptionResponse = new EmployeeNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public final ResponseEntity<?> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request){
    	UserAlreadyExistResponse exceptionResponse = new UserAlreadyExistResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}