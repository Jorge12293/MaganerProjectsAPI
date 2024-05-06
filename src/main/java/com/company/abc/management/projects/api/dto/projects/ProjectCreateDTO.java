package com.company.abc.management.projects.api.dto.projects;

import java.util.List;
import com.company.abc.management.projects.api.models.Task;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateDTO {
    private Long projectId;
	@NotEmpty(message = "no debe ser nulo o vacio")
	private String name;
	@NotEmpty(message = "no debe ser nulo o vacio")
	private String description;
	private List<Task> tasks;
}