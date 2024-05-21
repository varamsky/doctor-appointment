package com.example.doctor_appointment_be.user;

import com.example.doctor_appointment_be.common.DTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequestDTO implements DTO {
    @NotNull
    @Size(min = 2, message = "Name too short, please provide a valid name")
    private String name;
}
