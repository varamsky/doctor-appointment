package com.example.doctor_appointment_be.appointment;

import com.example.doctor_appointment_be.common.AppointmentStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public interface IDetailReport {
    @JsonProperty("appointment_date")
    LocalDate getAppointmentDate();

    @JsonProperty("patient_name")
    String getPatientName();

    @JsonProperty("appointment_status")
    AppointmentStatusEnum getAppointmentStatus();
}
