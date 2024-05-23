package com.example.doctor_appointment_be.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public interface ISummaryReport {
    @JsonProperty("appointment_date")
    LocalDate getAppointmentDate();

    @JsonProperty("number_of_appointments")
    int getNumberOfAppointments();

    @JsonProperty("number_of_cancelled_appointments")
    int getNumberOfCancelledAppointments();

    @JsonProperty("number_of_closed_appointments")
    int getNumberOfClosedAppointments();
}
