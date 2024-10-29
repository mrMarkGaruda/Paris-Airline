package com.epita.paris_airline.service;

import com.epita.paris_airline.model.Flight;
import com.epita.paris_airline.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            flight.setDepartureCity(flightDetails.getDepartureCity());
            flight.setArrivalCity(flightDetails.getArrivalCity());
            flight.setDepartureTime(flightDetails.getDepartureTime());
            flight.setArrivalTime(flightDetails.getArrivalTime());
            flight.setNumberOfSeats(flightDetails.getNumberOfSeats());
            flight.setDepartureAirport(flightDetails.getDepartureAirport());
            flight.setArrivalAirport(flightDetails.getArrivalAirport());
            flight.setPlane(flightDetails.getPlane());
            return flightRepository.save(flight);
        }
        return null;
    }

    public boolean deleteFlight(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate) {
        return flightRepository.findByDepartureCityAndArrivalCityAndDepartureTimeBetween(
                departureCity, arrivalCity, departureDate.atStartOfDay(), departureDate.atTime(LocalTime.MAX));
    }
}
