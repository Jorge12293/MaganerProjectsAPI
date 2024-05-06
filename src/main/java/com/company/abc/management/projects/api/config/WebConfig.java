package com.company.abc.management.projects.api.config;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mapea todos los endpoints
            .allowedOrigins("*") // Permite solicitudes desde cualquier origen
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Métodos permitidos
    }
}

