package org.makerminds.java.web.employeemanager.controller;

import jakarta.validation.Valid;

import org.makerminds.java.web.employeemanager.entity.AdminUser;
import org.makerminds.java.web.employeemanager.payload.LoginRequest;
import org.makerminds.java.web.employeemanager.payload.LoginSucessReponse;
import org.makerminds.java.web.employeemanager.security.JwtService;
import org.makerminds.java.web.employeemanager.service.AdminUserService;
import org.makerminds.java.web.employeemanager.service.CostumUserDetailsService;
import org.makerminds.java.web.employeemanager.service.MapValidationErrorService;
import org.makerminds.java.web.employeemanager.validator.PasswordValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class AdminUserController {
	private final MapValidationErrorService mapValidationErrorService;
	private final CostumUserDetailsService costumUserService;
	private final AdminUserService adminUserService;
	private final PasswordValidator passwordValidator;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
    


	public AdminUserController(MapValidationErrorService mapValidationErrorService,
			CostumUserDetailsService costumUserService, AdminUserService adminUserService,
			PasswordValidator passwordValidator, AuthenticationManager authenticationManager,
			JwtService jwtService) {
		this.mapValidationErrorService = mapValidationErrorService;
		this.costumUserService = costumUserService;
		this.adminUserService = adminUserService;
		this.passwordValidator = passwordValidator;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerNewUser(@Valid @RequestBody AdminUser newUser, BindingResult result){
		//validate passwords match
		passwordValidator.validate(newUser, result);
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}
		AdminUser user = adminUserService.saveUser(newUser);

		newUser.setConfirmPassword("");
		return new ResponseEntity<AdminUser>(user, HttpStatus.CREATED);
		
	}

    /**
     * @param loginRequest
     * @param result
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = "" + jwtService.generateToken(adminUserService.getUserByEmail(loginRequest.getUsername()));

        return ResponseEntity.ok(new LoginSucessReponse(jwt));
    }

	public CostumUserDetailsService getCostumUserService() {
		return costumUserService;
	}
	
}
