package com.example.doctor_appointment_be.doctor;

import com.example.doctor_appointment_be.common.IResourceService;

import java.util.List;
import java.util.UUID;

public interface IDoctorService extends IResourceService<Doctor, UUID, CreateDoctorDTO, UpdateDoctorRequestDTO> {
    @Override
    List<Doctor> getAll();

    @Override
    Doctor getById(UUID id);

    @Override
    Doctor save(CreateDoctorDTO input);

    @Override
    Doctor updateById(UUID id, UpdateDoctorRequestDTO input);

    @Override
    void deleteById(UUID id);
}
