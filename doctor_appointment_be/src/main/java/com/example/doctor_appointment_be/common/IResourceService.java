package com.example.doctor_appointment_be.common;

import com.example.doctor_appointment_be.user.User;

import java.util.List;
import java.util.UUID;

public interface IResourceService<T, ID, C extends DTO, U extends DTO> {
    List<T> getAll();

    T getById(ID id);

    T save(C input);

    T updateById(UUID id, U input);

    void deleteById(ID id);
}

