package com.example.doctor_appointment_be.audit;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditLog {
    private Object request;
    private String modifiedBy;
    private LocalDateTime date;
}