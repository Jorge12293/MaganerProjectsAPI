package com.company.abc.management.projects.api.models;
import com.company.abc.management.projects.api.enums.Status;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long taskId;
    
    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private String title;
    
    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private String description;
    
    @NotNull(message = "no debe ser nulo")
    private Status status;
    
    @NotNull(message = "no debe ser nulo")
    private Long projectId;
    
    private Long userId;
    
    private User user;
}
