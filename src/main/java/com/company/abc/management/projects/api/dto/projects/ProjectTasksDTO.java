package com.company.abc.management.projects.api.dto.projects;

import java.util.List;

import com.company.abc.management.projects.api.models.Project;
import com.company.abc.management.projects.api.models.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTasksDTO {
	private Long projectId;
	private String name;
	private String description;
	private List<Task> tasks;

	public ProjectTasksDTO(Project project,List<Task> tasks) {
		this.projectId = project.getProjectId();
		this.name = project.getName();
		this.description = project.getDescription();
		this.tasks = tasks;
	}

}