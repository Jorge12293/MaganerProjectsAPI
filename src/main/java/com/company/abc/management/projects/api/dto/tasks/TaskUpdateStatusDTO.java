package com.company.abc.management.projects.api.dto.tasks;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateStatusDTO {
    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private String status;
   
}