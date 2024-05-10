package com.example.doctor_appointment_be.auth;

import com.example.doctor_appointment_be.user.User;
import com.example.doctor_appointment_be.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService implements IAuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signup(RegisterRequestDTO input) {
        try {
            User user = new User();
            user.setName(input.getName());
            user.setEmail(input.getEmail());
            user.setPassword(passwordEncoder.encode(input.getPassword()));

            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException("User already exists for email - " + input.getEmail());
        }
    }

    @Override
    public User authenticate(LoginRequestDTO input) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword());
        // TODO: the authenticate() below silently sends 403 if email or password is wrong. Improve this to send message as well.
        authenticationManager.authenticate(authToken);

        User user = userRepository.findByEmail(input.getEmail());
        // TODO: is this check necessary as the above already checks if email and password are valid or not
        if (user == null) throw new UsernameNotFoundException("No user with email - " + input.getEmail());
        else return user;
    }
}
