package com.example.doctor_appointment_be.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {
    @NotNull
    @Size(min = 2, message = "Name too short, please provide a valid name")
    private String name;
}
