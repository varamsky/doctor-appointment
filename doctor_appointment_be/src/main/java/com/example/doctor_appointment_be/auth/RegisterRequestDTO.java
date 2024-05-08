package com.example.doctor_appointment_be.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;
}