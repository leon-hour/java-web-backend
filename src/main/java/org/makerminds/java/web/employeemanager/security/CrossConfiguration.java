package org.makerminds.java.web.employeemanager.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Specify the path(s) you want to enable CORS for
                .allowedOrigins("http://localhost:3000")  // Specify the allowed origin(s)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify the allowed HTTP methods
                .allowCredentials(true);  // Allow sending cookies along with the request
    }
}