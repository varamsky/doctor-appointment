package com.example.doctor_appointment_be.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
