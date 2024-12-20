package com.epita.paris_airline.service;

import com.epita.paris_airline.model.Booking;
import com.epita.paris_airline.model.Client;
import com.epita.paris_airline.model.Flight;
import com.epita.paris_airline.repository.BookingRepository;
import com.epita.paris_airline.repository.ClientRepository;
import com.epita.paris_airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Booking bookFlight(Long clientId, Long flightId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        Flight flight = flightRepository.findById(flightId).orElseThrow();
        Booking booking = new Booking();
        booking.setClient(client);
        booking.setFlight(flight);
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
