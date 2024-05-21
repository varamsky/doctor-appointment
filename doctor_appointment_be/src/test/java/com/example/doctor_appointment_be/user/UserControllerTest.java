package com.example.doctor_appointment_be.user;

import com.example.doctor_appointment_be.auth.JwtService;
import com.example.doctor_appointment_be.common.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
// Used for Slice Tests. Loads only the required Context instead of the complete Spring Application Context
@ExtendWith(SpringExtension.class)
@WithMockUser // to mock a user login
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean // Mock any services used by the controller
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @WithAnonymousUser
    @Test
    void shouldReturnUnauthorizedException() throws Exception {
        mockMvc
                .perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setName("User 1");
        User user2 = new User();
        user2.setName("User 2");
        List<User> users = List.of(user1, user2);

        // TODO: is mocking the userService acceptable? if yes then how will it test for user not found case? Should we find that here or in the Service tests?
        Mockito.when(userService.getAll()).thenReturn(users);

//        MvcResult mvcResult = mockMvc.perform(get("/users")).andReturn();
//        assertEquals(200, mvcResult.getResponse().getStatus());
        // OR use below
        mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
    }

    // checking how the controller translates exceptions to HTTPResponses
    @Test
    void shouldReturnBadRequestException() throws Exception {
        UUID id = UUID.randomUUID();
        String exceptionMessage = "User not found";

        Mockito.when(userService.getById(id)).thenThrow(new ResourceNotFoundException(exceptionMessage));
        mockMvc
                .perform(get("/users/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"error\":\"" + exceptionMessage + "\"}"));
        // TODO: Need to handle error message in global exception handler in a better way, which will enhance the testing here
    }

    @Test
    void shouldGetUserById() throws Exception {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setName("User 1");
        user.setUserId(id);

        Mockito.when(userService.getById(id)).thenReturn(user);
        mockMvc
                .perform(get("/users/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(user)));
    }
}


//@SpringBootTest // Used for Integration Tests. Loads the complete application context including security configuration
//@AutoConfigureMockMvc(addFilters = false)
//class UserControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void shouldFindAllUsers() throws Exception {
//        mockMvc.perform(get("/users")).andExpect(status().isOk());
//    }
//}