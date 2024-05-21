package com.example.doctor_appointment_be.common.util;

import com.example.doctor_appointment_be.doctor.Doctor;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilityTest {
    Doctor doctor;

    public TimeUtilityTest() {
        doctor = new Doctor();
        doctor.setDoctorId(UUID.fromString("80c5491f-d162-42a1-87a5-89094d3faf86"));
        doctor.setAppointmentSlotTime(Duration.ofMinutes(30));
        doctor.setDayStartTime(LocalTime.of(9, 0));
        doctor.setDayEndTime(LocalTime.of(17, 0));
    }

    @Test
    public void testValidWithBothTimes() {
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        boolean isValid = TimeUtility.validateStartAndEndTimes(startTime, endTime, doctor);

        assertTrue(isValid);
    }

    @Test
    public void testInValidWithBothTimes() {
        LocalTime startTime = LocalTime.of(18, 0);
        LocalTime endTime = LocalTime.of(10, 0);
        boolean isValid = TimeUtility.validateStartAndEndTimes(startTime, endTime, doctor);

        assertFalse(isValid);
    }

    @Test
    public void testValidStartTime() {
        LocalTime startTime = LocalTime.of(10, 0);
        boolean isValid = TimeUtility.validateStartAndEndTimes(startTime, null, doctor);

        assertTrue(isValid);
    }

    @Test
    public void testValidEndTime() {
        LocalTime endTime = LocalTime.of(19, 0);
        boolean isValid = TimeUtility.validateStartAndEndTimes(null, endTime, doctor);

        assertTrue(isValid);
    }

    @Test
    public void testInValidStartTime() {
        LocalTime startTime = LocalTime.of(19, 0);
        boolean isValid = TimeUtility.validateStartAndEndTimes(startTime, null, doctor);

        assertFalse(isValid);
    }

    @Test
    public void testInValidEndTime() {
        LocalTime endTime = LocalTime.of(8, 0);
        boolean isValid = TimeUtility.validateStartAndEndTimes(null, endTime, doctor);

        assertFalse(isValid);
    }
}