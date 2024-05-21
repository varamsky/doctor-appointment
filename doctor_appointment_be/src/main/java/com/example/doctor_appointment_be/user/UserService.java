package com.example.doctor_appointment_be.user;

import com.example.doctor_appointment_be.common.DTO;
import com.example.doctor_appointment_be.common.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ResourceNotFoundException("User not found for id - " + id);
        else return user.get();
    }

    /**
     * Ignore this method here.
     * This is just a placeholder as this class implements IUserService->IResourceService
     * Use AuthenticationService to create users
     */
    @Override
    public User save(DTO input) {
        // only a placeholder here
        // use AuthenticationService to create users
        return null;
    }

    @Override
    public User updateById(UUID id, UpdateUserRequestDTO input) {
        User user = this.getById(id);
        user.setName(input.getName());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        User user = this.getById(id);
        userRepository.deleteById(id);
    }
}
