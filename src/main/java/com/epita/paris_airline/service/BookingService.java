@Service
public class BookingService {
    @Autowired private BookingRepository bookingRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private FlightRepository flightRepository;

    public Booking bookFlight(Long clientId, Long flightId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        Flight flight = flightRepository.findById(flightId).orElseThrow();
        Booking booking = new Booking();
        booking.setClient(client);
        booking.setFlight(flight);
        return bookingRepository.save(booking);
    }
}
