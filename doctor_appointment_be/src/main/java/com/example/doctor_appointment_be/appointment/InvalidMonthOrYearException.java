package com.example.doctor_appointment_be.appointment;

public class InvalidMonthOrYearException extends RuntimeException {
    public InvalidMonthOrYearException(String message) {
        super(message);
    }
}
