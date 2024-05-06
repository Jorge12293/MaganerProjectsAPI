package com.company.abc.management.projects.api.dto.tasks;

import com.company.abc.management.projects.api.enums.Status;
import com.company.abc.management.projects.api.models.Project;
import com.company.abc.management.projects.api.models.Task;
import com.company.abc.management.projects.api.models.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUserDTO {
    private Long taskId;
    private String title;
    private String description;
    private Status status;
    private Long projectId;
    private Project project;
    
}