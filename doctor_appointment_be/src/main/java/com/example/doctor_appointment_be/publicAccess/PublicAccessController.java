package com.example.doctor_appointment_be.publicAccess;

import com.example.doctor_appointment_be.doctor.Doctor;
import com.example.doctor_appointment_be.doctor.IDoctorService;
import com.example.doctor_appointment_be.user.IUserService;
import com.example.doctor_appointment_be.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/public")
@AllArgsConstructor
public class PublicAccessController {

    private final IDoctorService doctorService;
    private final IUserService userService;

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getById(id));
    }
}
