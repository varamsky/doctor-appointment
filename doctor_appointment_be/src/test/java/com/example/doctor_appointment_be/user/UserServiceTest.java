package com.example.doctor_appointment_be.user;

import com.example.doctor_appointment_be.common.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // TODO: why SpringExtension and @MockBean for Controller testing
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void shouldGetAllUsers() {
        User user1 = new User();
        user1.setName("User 1");
        User user2 = new User();
        user2.setName("User 2");
        List<User> users = List.of(user1, user2);
        Mockito.when(userRepository.findAll()).thenReturn(users);

        assertEquals(userService.getAll(), users);
    }

    @Test
    void shouldThrowResourceNotFoundException() {
        UUID id = UUID.randomUUID();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getById(id));
    }
}