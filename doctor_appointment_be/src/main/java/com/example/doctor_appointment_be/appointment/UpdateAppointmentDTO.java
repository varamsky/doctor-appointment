package com.example.doctor_appointment_be.appointment;

import com.example.doctor_appointment_be.common.AppointmentStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateAppointmentDTO {
    @NotNull
    @JsonProperty(value = "appointment_status")
    // TODO: catch exception if provided string is not a valid AppointmentStatusEnum
    private AppointmentStatusEnum appointmentStatus;
}
