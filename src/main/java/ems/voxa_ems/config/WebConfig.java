package ems.voxa_ems.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Configuration for CORS (Cross-Origin Resource Sharing)
 * 
 * WHAT IS CORS?
 * - By default, browsers block requests from one domain to another (security
 * feature)
 * - Your Angular app runs on http://localhost:4200
 * - Your Spring Boot API runs on http://localhost:8080
 * - Without CORS, Angular cannot call your API
 * 
 * This configuration allows Angular to communicate with Spring Boot.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply CORS to all /api/* endpoints
                .allowedOrigins("http://localhost:4200") // Allow Angular app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow cookies/credentials
    }
}
