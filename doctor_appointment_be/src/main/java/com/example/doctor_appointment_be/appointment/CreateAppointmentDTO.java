package com.example.doctor_appointment_be.appointment;

import com.example.doctor_appointment_be.common.AppointmentStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class CreateAppointmentDTO {
    @NotNull
    @JsonProperty(value = "appointment_date")
    // TODO: pattern="dd/MM/yyyy" is not working
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent
    private LocalDate appointmentDate;

    @NotNull
    @JsonProperty(value = "appointment_time")
    // TODO: catch exception if provided string is not a valid time
    private LocalTime appointmentTime; // start time

    @NotNull
    @JsonProperty(value = "doctor_id")
    private UUID doctorId;

    @NotNull
    @Size(min = 3, message = "Patient name is too short")
    @JsonProperty(value = "patient_name")
    private String patientName;

    @NotNull
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please provide a valid email")
    @JsonProperty(value = "patient_email")
    private String patientEmail;

    @NotNull
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Phone number must contain 10 digits and start with a number between 6-9")
    @JsonProperty(value = "patient_phone")
    private String patientPhone;

    @NotNull
    @JsonProperty(value = "appointment_status")
    // TODO: catch exception if provided string is not a valid AppointmentStatusEnum
    private AppointmentStatusEnum appointmentStatus;
}
