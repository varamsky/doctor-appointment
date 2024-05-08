package com.example.doctor_appointment_be.auth;

import com.example.doctor_appointment_be.user.User;
import com.example.doctor_appointment_be.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;

    @Override
    public User signup(RegisterRequestDTO input) {
        try {
            User user = new User();
            user.setName(input.getName());
            user.setEmail(input.getEmail());
            user.setPassword(input.getPassword());

            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException("User already exists for email - " + input.getEmail());
        }
    }

    @Override
    public User authenticate(LoginRequestDTO input) {
        return userRepository.findByEmail(input.getEmail());
    }
}
