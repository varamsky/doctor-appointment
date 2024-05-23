package com.example.doctor_appointment_be.appointment;

import java.time.LocalDate;

public interface ISummaryReport {
    LocalDate getAppointmentDate();

    int getNumberOfAppointments();

    int getNumberOfCancelledAppointments();

    int getNumberOfClosedAppointments();
}
