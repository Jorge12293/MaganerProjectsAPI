package com.company.abc.management.projects.api.config;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
		info= @Info(
				title = "Managment Projects API",
				version= "1.0",
				description = "Documentation for endpoints in Managment Projects API")
		
)
public class OpenApiConfig {

}
