package com.epita.paris_airline.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Employee extends User {
    private String employeeNumber;
}
