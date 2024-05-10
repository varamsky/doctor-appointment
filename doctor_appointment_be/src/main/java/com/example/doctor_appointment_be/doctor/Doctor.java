package com.example.doctor_appointment_be.doctor;

import com.example.doctor_appointment_be.user.User;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID doctorId;

    @Column(nullable = false)
    private String professionalName;

    // TODO: why saving a doctor not works when using @OneToOne(cascade = CascadeType.ALL)?
//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonBackReference
    @JsonIncludeProperties(value = {"userId"})
    private User user;

    @Column(nullable = false)
    // Duration takes values in seconds. So, 30 is 30 seconds. 1800 is 1800 seconds -> 30 minutes
    private Duration appointmentSlotTime; // min 15minutes -> max 60minutes

    @Column(nullable = false)
    private LocalTime dayStartTime;

    @Column(nullable = false)
    private LocalTime dayEndTime;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
