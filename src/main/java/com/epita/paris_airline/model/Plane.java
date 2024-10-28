package com.epita.paris_airline.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "planes")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int manufacturingYear;
}
