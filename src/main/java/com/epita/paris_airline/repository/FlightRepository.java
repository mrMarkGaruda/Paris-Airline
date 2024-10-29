package com.epita.paris_airline.repository;

import com.epita.paris_airline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureCityAndArrivalCityAndDepartureTimeBetween(
            String departureCity, String arrivalCity, LocalDateTime start, LocalDateTime end);
}
