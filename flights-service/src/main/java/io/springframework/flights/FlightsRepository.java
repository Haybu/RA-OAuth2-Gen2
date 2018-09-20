package io.springframework.flights;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface FlightsRepository extends ReactiveCrudRepository<Flight, ObjectId> {

    @Query("{ 'origin': ?0, 'destination': ?1, 'departure':{ $gt: ?2, $lt: ?3} }")
    public Flux<Flight> findFlightsByCustomQueryDated(String origin,
                                                      String destination,
                                                      LocalDate mindate,
                                                      LocalDate maxdate);
}
