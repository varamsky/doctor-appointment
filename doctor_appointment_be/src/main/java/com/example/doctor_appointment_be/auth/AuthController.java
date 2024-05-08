package com.example.doctor_appointment_be.auth;

import com.example.doctor_appointment_be.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.ok(errors.getAllErrors().toString());
        }

        User registeredUser = authenticationService.signup(registerRequestDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        User authenticatedUser = authenticationService.authenticate(loginRequestDTO);

//        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken("token = " + authenticatedUser.getName());
        loginResponse.setExpiresIn(1234);

        return ResponseEntity.ok(loginResponse);
    }
}
