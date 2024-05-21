package com.example.doctor_appointment_be.doctor;

import jakarta.validation.Valid;
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

    private final IDoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody @Valid CreateDoctorDTO createDoctorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.save(createDoctorDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable UUID id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctorById(@PathVariable UUID id, @RequestBody @Valid UpdateDoctorRequestDTO updateDoctorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.updateById(id, updateDoctorRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable UUID id) {
        doctorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
