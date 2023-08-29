package org.makerminds.java.web.employeemanager.validator;


import org.makerminds.java.web.employeemanager.entity.AdminUser;
import org.springframework.stereotype.Component;
	import org.springframework.validation.Errors;
	import org.springframework.validation.Validator;

	@Component
	public class PasswordValidator implements Validator {

	    @Override
	    public boolean supports(Class<?> aClass) {
	        return AdminUser.class.equals(aClass);
	    }
	    public static boolean isPasswordValid(String password) {
	        String pattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
	        return password.matches(pattern);
	    }

	    @Override
	    public void validate(Object object, Errors errors) {

	    	AdminUser user = (AdminUser) object;

	        if(!isPasswordValid(user.getPassword())){
	            errors.rejectValue("password","Invalid", "password must contain at least eight characters, at least one number and special characters");
	        }

	        if(!user.getPassword().equals(user.getConfirmPassword())){
	            errors.rejectValue("confirmPassword","Match", "Passwords must match");

	        }
	    }
	}