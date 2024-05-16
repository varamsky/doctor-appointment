package com.example.doctor_appointment_be.appointment;

import com.example.doctor_appointment_be.common.AppointmentStatusEnum;
import com.example.doctor_appointment_be.doctor.Doctor;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentUtilityTest {
    List<Appointment> appointments;
    Duration slotTime;
    Doctor doctor;

    public AppointmentUtilityTest() {
        doctor = new Doctor();
        doctor.setDoctorId(UUID.fromString("80c5491f-d162-42a1-87a5-89094d3faf86"));
        doctor.setAppointmentSlotTime(Duration.ofMinutes(30));
        doctor.setDayStartTime(LocalTime.of(9, 0));
        doctor.setDayEndTime(LocalTime.of(17, 0));

        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentId(UUID.fromString("06f63e09-d69b-4554-8776-273af2cb546e"));
        appointment1.setAppointmentDate(LocalDate.of(2024, 5, 16));
        appointment1.setAppointmentTime(LocalTime.of(12, 0));
        appointment1.setDoctor(doctor);
        appointment1.setAppointmentStatus(AppointmentStatusEnum.OPEN);

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentId(UUID.fromString("06f63e09-d69b-4554-8776-273af2cb546f"));
        appointment2.setAppointmentDate(LocalDate.of(2024, 5, 16));
        appointment2.setAppointmentTime(LocalTime.of(15, 0));
        appointment2.setDoctor(doctor);
        appointment2.setAppointmentStatus(AppointmentStatusEnum.OPEN);

        appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);

        slotTime = doctor.getAppointmentSlotTime();
    }

    @Test
    public void testValidAppointment() {
        LocalTime time = LocalTime.of(13, 0);
        boolean isValid = AppointmentUtility.validateAppointment(time, appointments, slotTime);

        assertTrue(isValid);
    }

    @Test
    public void testInValidAppointment() {
        LocalTime time = LocalTime.of(12, 20);
        boolean isValid = AppointmentUtility.validateAppointment(time, appointments, slotTime);

        assertFalse(isValid);
    }
}