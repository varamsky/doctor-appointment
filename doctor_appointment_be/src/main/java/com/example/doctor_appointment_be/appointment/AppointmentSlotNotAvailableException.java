package com.example.doctor_appointment_be.appointment;

public class AppointmentSlotNotAvailableException extends RuntimeException {
    public AppointmentSlotNotAvailableException(String message) {
        super(message);
    }
}
