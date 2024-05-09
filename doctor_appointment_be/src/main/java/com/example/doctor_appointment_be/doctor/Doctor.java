package com.example.doctor_appointment_be.doctor;

import com.example.doctor_appointment_be.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID doctorId;

    @Column(nullable = false)
    private String professionalName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private Duration appointmentSlotTime; // min 15minutes -> max 60minutes

    @Column(nullable = false)
    private LocalTime dayStartTime;

    @Column(nullable = false)
    private LocalTime dayEndTime;
}
