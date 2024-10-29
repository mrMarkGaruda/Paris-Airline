package com.epita.paris_airline.service;

import com.epita.paris_airline.model.Airport;
import com.epita.paris_airline.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport updateAirport(Long id, Airport airportDetails) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        if (optionalAirport.isPresent()) {
            Airport airport = optionalAirport.get();
            airport.setName(airportDetails.getName());
            airport.setCountry(airportDetails.getCountry());
            airport.setCity(airportDetails.getCity());
            return airportRepository.save(airport);
        }
        return null;
    }

    public boolean deleteAirport(Long id) {
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
