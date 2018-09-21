/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import java.text.ParseException;
import java.time.LocalDate;
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
	public Mono<String> book(ReservationRequest reservationRequest) {
		log.info("reserving in flight " + reservationRequest.getFlightId());

		Mono<Reservation> reservation = this.bookFlight(reservationRequest.getFlightId(),
				reservationRequest.getPassengers(),
                reservationRequest.getAddress());

		return reservation.map(r -> r.getConfirmationNumber());
	}

	private Mono<Reservation> bookFlight(String flightId, List<Passenger> passengers
			, Address address) {

	    final Boolean[] seatsReserved = {false};

	    Function<Flight, Flight> reserveSeats = (f) -> {
            if (f.getCapacity() - passengers.size() >= 0) {
                f.setCapacity(f.getCapacity() - passengers.size());
                seatsReserved[0] = true;
            }
            return f;
        };

	   return this.findById(flightId)
                .map(f -> reserveSeats.apply(f))
                .doOnNext(f -> flightClient.update(f))
                .flatMap(f -> {
                    Reservation reservation = new Reservation();
                    if (seatsReserved[0]) {
                        log.info("Reserving a flight...");
                        passengers.stream().forEach(p -> p.setAddress(address));
                        reservation.setOrigin(f.getOrigin());
                        reservation.setDestination(f.getDestination());
                        reservation.setConfirmationNumber(UUID.randomUUID().toString());
                        reservation.setFlightNumber(f.getNbr());
                        reservation.setPassengers(passengers);
                        reservation.setDeparture(f.getDeparture());
                    }
                    return reservationRepository.save(reservation);
                });
	}

	public Mono<String> reliableBooking(ReservationRequest reservationRequest) {
		log.info(
				"Something went wrong. Circuit breaker opens an a temporary booking is generated");
		return Mono.just("No Confirmation Number Generated");
	}

	public Flux<Flight> searchDatedFlights(String from, String to, LocalDate minDate,
			LocalDate maxDate) throws ParseException {
		log.info("searching flights from " + from + " to " + to + " between " + minDate
				+ " and " + maxDate);
		return flightClient.findDatedFlights(from, to, minDate, maxDate);
	}

	public Flux<Flight> searchFlights(String from, String to) {
		log.info("searching flights from " + from + " to " + to);
		return flightClient.findFlights(from, to);
	}

    public Flux<Flight> searchAllFlights() {
        log.info("searching flights on reservations service bean");
        return flightClient.findAllFlights();
    }

    public Flux<Reservation> allReservations() {
        log.info("retrieving all reservations on reservations service bean");
        return reservationRepository.findAll();
    }


	public Flux<String> getFlightOrigins() {
		return flightClient.origins();
	}

	public Flux<String> getFlightDestinations() {
		return flightClient.destinations();
	}

	public Mono<Flight> findById(String flightId) {
		return flightClient.findById(flightId);
	}

}
