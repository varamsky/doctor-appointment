package com.example.doctor_appointment_be.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResponseDTO {
    private String token;
    private long expiresIn;
}