package com.example.doctor_appointment_be.publicAccess;

import com.example.doctor_appointment_be.appointment.Appointment;
import com.example.doctor_appointment_be.appointment.AppointmentService;
import com.example.doctor_appointment_be.appointment.CreateAppointmentDTO;
import com.example.doctor_appointment_be.appointment.GetAppointmentsByDoctorAndAppointmentDateRequestDTO;
import com.example.doctor_appointment_be.doctor.Doctor;
import com.example.doctor_appointment_be.doctor.IDoctorService;
import com.example.doctor_appointment_be.user.IUserService;
import com.example.doctor_appointment_be.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/public")
@AllArgsConstructor
public class PublicAccessController {

    private final IDoctorService doctorService;
    private final IUserService userService;
    private final AppointmentService appointmentService;

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable UUID id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    @GetMapping("/appointments/by_doctor_and_appointment_date")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorAndAppointmentDate(@RequestParam("appointment_date") LocalDate appointmentDate, @RequestParam("doctor_id") UUID doctorId) {
        GetAppointmentsByDoctorAndAppointmentDateRequestDTO input = new GetAppointmentsByDoctorAndAppointmentDateRequestDTO();
        input.setAppointmentDate(appointmentDate);
        input.setDoctorId(doctorId);
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorAndAppointmentDate(input));
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid CreateAppointmentDTO createAppointmentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(createAppointmentDTO));
    }
}
