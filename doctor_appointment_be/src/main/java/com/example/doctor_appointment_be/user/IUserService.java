package com.example.doctor_appointment_be.user;

import com.example.doctor_appointment_be.common.DTO;
import com.example.doctor_appointment_be.common.IResourceService;

import java.util.List;
import java.util.UUID;

public interface IUserService extends IResourceService<User, UUID, DTO, UpdateUserRequestDTO> {
    // DTO in the generic definition of this interface is just a placeholder as we don't have the create user method here. It is managed by the AuthenticationService class
    @Override
    List<User> getAll();

    @Override
    User getById(UUID id);

    @Override
    User updateById(UUID id, UpdateUserRequestDTO input);

    @Override
    void deleteById(UUID id);
}
