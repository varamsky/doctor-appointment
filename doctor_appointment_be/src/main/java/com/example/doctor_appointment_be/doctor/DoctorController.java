package com.example.doctor_appointment_be.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctors")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

//    @GetMapping
//    public String getAllDoctors() {
//        return "dd";
//    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody @Valid CreateDoctorDTO createDoctorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(createDoctorDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable UUID id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable UUID id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }
}
