package com.example.doctor_appointment_be.doctor;

import com.example.doctor_appointment_be.common.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

import java.time.Duration;
import java.time.LocalTime;

@Data
public class UpdateDoctorRequestDTO implements DTO {
    @Size(min = 2, message = "Professional designation too short, please provide a valid name")
    @JsonProperty("professional_name")
    private String professionalName;

    @DurationMin(minutes = 15,message = "Slot time too small. Minimum 900s -> 15m is allowed")
    @DurationMax(minutes = 60, message = "Slot time too large. Maximum 3600s -> 60m is allowed")
    @JsonProperty("appointment_slot_time")
    private Duration appointmentSlotTime;

    @JsonProperty("day_start_time")
    // TODO: need to find to handle HttpMessageNotReadableException when invalid time is entered. Same for dayEndTime.
    private LocalTime dayStartTime;

    @JsonProperty("day_end_time")
    private LocalTime dayEndTime;
}
