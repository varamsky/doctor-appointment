package com.example.doctor_appointment_be.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("User not found for id - " + id);
        else return user.get();
    }

    public User updateUserById(UUID id, UpdateUserRequestDTO input) {
        User user = this.getUserById(id);
        user.setName(input.getName());
        return userRepository.save(user);
    }

    public void deleteUserById(UUID id) {
        User user = this.getUserById(id);
        userRepository.deleteById(id);
    }
}
