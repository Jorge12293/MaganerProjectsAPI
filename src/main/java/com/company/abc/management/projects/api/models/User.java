package com.company.abc.management.projects.api.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    @NotEmpty(message = "no debe ser nulo o vacio")
    private String uid;
    @NotEmpty(message = "no debe ser nulo o vacio")
    private String username;
    @Email
    @NotEmpty(message = "no debe ser nulo o vacio")
    private String email;
}
