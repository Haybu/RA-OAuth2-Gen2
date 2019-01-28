package io.agilehandy.reservation;

import io.agilehandy.reservation.entities.Address;
import io.agilehandy.reservation.entities.Passenger;
import io.agilehandy.reservation.entities.Reservation;
import io.agilehandy.reservation.entities.ReservationRequest;
import io.agilehandy.reservation.flight.Flight;
import io.agilehandy.reservation.flight.FlightClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author Haytham Mohamed
 */

@Service
@Slf4j
public class ReservationService {

	private final FlightClient flightClient;

	private final ReservationRepository reservationRepository;

	public ReservationService(FlightClient flightClient,
			ReservationRepository reservationRepository) {
		this.flightClient = flightClient;
		this.reservationRepository = reservationRepository;
	}

	// @HystrixCommand(fallbackMethod = "reliableBooking")
	public Mono<String> book(ReservationRequest request) {
		log.debug("reserving in flight " + request.getFlightId());

		Mono<Reservation> reservation = this.bookFlight(request.getFlightId(),
				request.getPassengers(), request.getAddress(),
				request.getUserName(), request.getEmail());

		return reservation.map(r -> r.getConfirmationNumber());
	}

	private Mono<Reservation> bookFlight(String flightId, List<Passenger> passengers,
			Address address, String userName, String email) {

		final Boolean[] seatsReserved = { false };

		Function<Flight, Flight> reserveSeats = (f) -> {
			int size = 1;
			if (passengers != null && !passengers.isEmpty()) {
				size = passengers.size();
			}
			if (f.getCapacity() - size >= 0) {
				f.setCapacity(f.getCapacity() - size);
				seatsReserved[0] = true;
			}
			return f;
		};

		return this.findById(flightId).map(f -> reserveSeats.apply(f))
				.doOnNext(f -> flightClient.update(f)).flatMap(f -> {
					Reservation reservation = new Reservation();
					if (seatsReserved[0]) {
						log.info("Reserving a flight...");
						if (passengers != null && !passengers.isEmpty()) {
							passengers.stream().forEach(p -> p.setAddress(address));
							reservation.setPassengers(passengers);
						}
						reservation.setOrigin(f.getOrigin());
						reservation.setDestination(f.getDestination());
						reservation.setConfirmationNumber(UUID.randomUUID().toString());
						reservation.setFlightNumber(f.getNbr());
						reservation.setDeparture(f.getDeparture());
						reservation.setUserName(userName);
						reservation.setEmail(email);
					}
					return reservationRepository.save(reservation);
				});
	}

	public Mono<String> reliableBooking(ReservationRequest reservationRequest) {
		log.debug(
				"Something went wrong. Circuit breaker opens an a temporary booking is generated");
		return Mono.just("No Confirmation Number Generated");
	}

	public Flux<Reservation> allReservations() {
		log.debug("retrieving all reservations on reservations service bean");
		return reservationRepository.findAll();
	}

	public Mono<Flight> findById(String flightId) {
		return flightClient.findById(flightId);
	}

}
