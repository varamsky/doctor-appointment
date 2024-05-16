package com.example.doctor_appointment_be.appointment;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/by_doctor_and_appointment_date")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorAndAppointmentDate(@RequestBody @Valid GetAppointmentsByDoctorAndAppointmentDateDTO input) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorAndAppointmentDate(input));
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid CreateAppointmentDTO createAppointmentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(createAppointmentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointmentById(@PathVariable UUID id, @RequestBody @Valid UpdateAppointmentDTO input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.updateAppointment(id, input));
    }
}