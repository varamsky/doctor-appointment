package com.example.doctor_appointment_be.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class GetAppointmentsByDoctorAndAppointmentDateDTO {
    @NotNull
    @JsonProperty(value = "appointment_date")
    // TODO: pattern="dd/MM/yyyy" is not working
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent
    private LocalDate appointmentDate;

    @NotNull
    @JsonProperty(value = "doctor_id")
    private UUID doctorId;
}
