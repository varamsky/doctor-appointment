package com.example.doctor_appointment_be.appointment;

import com.example.doctor_appointment_be.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> getAppointmentsByDoctorAndAppointmentDate(Doctor doctor, LocalDate appointmentDate);
}
