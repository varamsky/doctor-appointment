package com.example.doctor_appointment_be.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    // TODO: find a better way to name this. This should have been findByUserId
    Optional<Doctor> findByUserUserId(UUID id);
}
