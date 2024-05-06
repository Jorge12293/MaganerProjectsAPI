package com.company.abc.management.projects.api.dto.filters;

import java.util.List;
import com.company.abc.management.projects.api.models.Project;
import com.company.abc.management.projects.api.models.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterProjectsTasksDTO {
	 private List<Project> listProject;
	 private List<Task> listTask;
}
