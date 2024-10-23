package com.epita.paris_airline.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private Double price;
}
