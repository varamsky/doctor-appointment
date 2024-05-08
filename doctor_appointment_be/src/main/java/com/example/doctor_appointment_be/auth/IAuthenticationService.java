package com.example.doctor_appointment_be.auth;

import com.example.doctor_appointment_be.user.User;

public interface IAuthenticationService {
    User signup(RegisterRequestDTO input);
    User authenticate(LoginRequestDTO input);
}
