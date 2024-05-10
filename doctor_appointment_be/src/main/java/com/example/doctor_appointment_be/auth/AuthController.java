package com.example.doctor_appointment_be.auth;

import com.example.doctor_appointment_be.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final IAuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        User registeredUser = authenticationService.signup(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        User authenticatedUser = authenticationService.authenticate(loginRequestDTO);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
