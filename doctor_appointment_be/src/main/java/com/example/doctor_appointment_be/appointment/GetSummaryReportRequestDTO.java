package com.example.doctor_appointment_be.appointment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetSummaryReportRequestDTO {
    @NotNull
    @Min(value = 1, message = "Please provide a valid month number, minimum is 1")
    @Max(value = 12, message = "Please provide a valid month number, maximum is 12")
    private int month;

    @NotNull
    private int year;
}
