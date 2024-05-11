package com.example.doctor_appointment_be.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class CreateDoctorDTO {
    @NotNull
    @Size(min = 5, message = "Professional designation is too short")
    @JsonProperty(value = "professional_name")
    private String professionalName;

    // TODO: catch exception if provided string is not a valid UUID. This works in places like User Controller getUserById(@PathVariable UUID id) why?
    @NotNull
    @JsonProperty(value = "user_id")
    private UUID userId;

    @NotNull
    @JsonProperty(value = "appointment_slot_time")
    // min 15minutes -> max 60minutes
    // Duration takes values in seconds. So, 30 is 30 seconds. 1800 is 1800 seconds -> 30 minutes
    private Duration appointmentSlotTime;

    @NotNull
    @JsonProperty(value = "day_start_time")
    private LocalTime dayStartTime;

    @NotNull
    @JsonProperty(value = "day_end_time")
    private LocalTime dayEndTime;
}
