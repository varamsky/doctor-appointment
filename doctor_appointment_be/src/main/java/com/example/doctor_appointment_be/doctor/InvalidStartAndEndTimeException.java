package com.example.doctor_appointment_be.doctor;

public class InvalidStartAndEndTimeException extends RuntimeException {
    public InvalidStartAndEndTimeException(String message) {
        super(message);
    }
}
