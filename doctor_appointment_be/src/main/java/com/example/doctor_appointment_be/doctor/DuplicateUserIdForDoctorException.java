package com.example.doctor_appointment_be.doctor;

public class DuplicateUserIdForDoctorException extends RuntimeException {
    public DuplicateUserIdForDoctorException(String message) {
        super(message);
    }
}
