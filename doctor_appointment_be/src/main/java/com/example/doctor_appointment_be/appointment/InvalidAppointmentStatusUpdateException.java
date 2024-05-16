package com.example.doctor_appointment_be.appointment;

// Appointment in OPEN status can only be changed to CLOSED or CANCELLED
public class InvalidAppointmentStatusUpdateException extends RuntimeException {
    public InvalidAppointmentStatusUpdateException(String message) {
        super(message);
    }
}
