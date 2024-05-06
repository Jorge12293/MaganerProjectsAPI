package com.company.abc.management.projects.api.dto.tasks;

import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.models.Project;
import com.company.abc.management.projects.api.models.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskProjectDTO {
    private Long taskId;
    private String title;
    private String description;
    private Status status;
    private Project project;

	public TaskProjectDTO(Task task,Project project) {
	   this.taskId=task.getTaskId();
	   this.title=task.getTitle();
	   this.description=task.getDescription();
	   this.status=task.getStatus();
	   this.project=project;
	}

}