package com.example.doctor_appointment_be.appointment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetSummaryReportResponseDTO {
    private LocalDate appointmentDate;
    private int numberOfAppointments;
    private int numberOfCancelledAppointments;
    private int numberOfClosedAppointments;
}
