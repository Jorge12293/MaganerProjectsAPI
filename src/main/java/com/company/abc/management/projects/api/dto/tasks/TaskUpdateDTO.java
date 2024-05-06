package com.company.abc.management.projects.api.dto.tasks;

import com.company.abc.management.projects.api.enums.Status;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateDTO {
    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private String title;
    
    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private String description;
    
    @NotNull(message = "no debe ser nulo")
    private Status status;
   
    private Long userId;
}