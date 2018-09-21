package io.agilehandy.reservation;

import io.agilehandy.reservation.entities.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, String> {

}
