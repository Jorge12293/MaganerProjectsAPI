package com.company.abc.management.projects.api.dto.projects;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateDTO {
	@NotEmpty(message = "no debe ser nulo o vacio")
	private String name;
	@NotEmpty(message = "no debe ser nulo o vacio")
	private String description;
}