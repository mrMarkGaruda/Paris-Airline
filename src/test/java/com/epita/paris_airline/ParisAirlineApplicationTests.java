package com.epita.paris_airline;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ParisAirlineApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // User Tests
    @Test
    void createUser() throws Exception {
        String userJson = """
        {
          "firstname": "John",
          "lastname": "Doe",
          "address": "123 Main St",
          "email": "john.doe@example.com",
          "phone": "1234567890",
          "birthdate": "1985-05-15"
        }""";

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.lastname", is("Doe")));
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));
    }

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void updateUser() throws Exception {
        String updatedUserJson = """
        {
          "firstname": "John",
          "lastname": "Doe",
          "address": "456 New St",
          "email": "john.doe@example.com",
          "phone": "9876543210",
          "birthdate": "1985-05-15"
        }""";

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is("456 New St")));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }

    // Add similar test cases for Client, Employee, Plane, Airport, Flight, and Booking entities...

    // Example for Plane
    @Test
    void createPlane() throws Exception {
        String planeJson = """
        {
          "brand": "Boeing",
          "model": "737",
          "manufacturingYear": 2015
        }""";

        mockMvc.perform(post("/api/planes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(planeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand", is("Boeing")));
    }

    @Test
    void getAllPlanes() throws Exception {
        mockMvc.perform(get("/api/planes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));
    }

    // Flight Search Test
    @Test
    void searchFlights() throws Exception {
        mockMvc.perform(get("/api/flights/search")
                        .param("departureCity", "Paris")
                        .param("arrivalCity", "London")
                        .param("departureDate", "2024-12-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));
    }

    // Booking Test
    @Test
    void createBooking() throws Exception {
        mockMvc.perform(post("/api/bookings")
                        .param("clientId", "1")
                        .param("flightId", "1"))
                .andExpect(status().isCreated());
    }
}
