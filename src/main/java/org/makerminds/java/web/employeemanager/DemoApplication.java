 package org.makerminds.java.web.employeemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run() is a key method in a Spring Boot application that starts up the application's ApplicationContext and web server, 
		// allowing it to handle incoming requests and respond accordingly. 
		SpringApplication.run(DemoApplication.class, args);
	}  
}
