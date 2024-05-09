package com.example.doctor_appointment_be.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class RegisterRequestDTO {
    @NotNull
    @Size(min = 2, message = "Name too short, please provide a valid name")
    private String name;

    @NotNull
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please provide a valid email")
    private String email;

    @NotNull
    @Size(min = 5, message = "Password too short, please provide longer password")
    @Size(max=30, message = "Password is too long, please provide a shorter password")
    private String password;
}