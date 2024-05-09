package com.example.doctor_appointment_be.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestDTO {
    @NotNull
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please provide a valid email")
    private String email;

    @NotNull
    @Size(min = 5, message = "Password too short")
    @Size(max=30, message = "Password is too long")
    private String password;
}
