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

import io.agilehandy.reservation.entities.Reservation;
import io.agilehandy.reservation.entities.ReservationRequest;
import io.agilehandy.reservation.exceptions.ExceptionMessage;
import io.agilehandy.reservation.exceptions.ReservationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Haytham Mohamed
 */

@RestController
@Slf4j
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public Flux<Reservation> allReservations() {
		log.debug("retrieve all reservations");
		return reservationService.allReservations();
	}

	@PostMapping
	public Mono<ReservationRequest> reserve(@RequestBody ReservationRequest reservationRequest) {
		log.debug("Booking a flight for " + reservationRequest.getPassengers());
		Mono<String> confirmation = reservationService.book(reservationRequest);
		return confirmation.log().doOnNext(c -> reservationRequest.setConfirmation(c))
				.map(s -> reservationRequest);
	}

	@ExceptionHandler(ReservationException.class)
	public ExceptionMessage handleException(ReservationException exception) {
		return new ExceptionMessage(exception.getExceptionMsg(),
				exception.getCause() == null ? "" : exception.getCause().getMessage(),
				Arrays.stream(exception.getStackTrace())
						.map(sequence -> String.valueOf(sequence))
						.collect(Collectors.joining("\n")));
	}

}
