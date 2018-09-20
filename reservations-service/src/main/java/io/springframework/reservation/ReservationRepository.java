package io.springframework.reservation;

import io.springframework.reservation.entities.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, Long> {

}
