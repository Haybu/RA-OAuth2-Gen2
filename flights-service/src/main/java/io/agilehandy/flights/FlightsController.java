package io.agilehandy.flights;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@Slf4j
public class FlightsController {

	private final FlightsRepository flightsRepository;

	public FlightsController(FlightsRepository flightsRepository) {
		this.flightsRepository = flightsRepository;
	}

	@GetMapping("/search/datedlegs")
	public Flux<Flight> datedFlights(@RequestParam String origin
			, @RequestParam String destination
			, @RequestParam LocalDate minDate
			, @RequestParam LocalDate maxDate)
	{
		return flightsRepository
				.findFlightsByCustomQueryDated(origin, destination, minDate, maxDate);
	}

	@GetMapping("/{id}")
	public Mono<Flight> FlightById(@PathVariable String id) {
		return flightsRepository.findById(new ObjectId(id));
	}

	@GetMapping("/")
	public Flux<Flight> allFlights() {
		return flightsRepository.findAll();
	}

	@PostMapping("/")
	public Mono<Flight> updateFlight(@RequestBody Flight flight) {
		return flightsRepository.save(flight);
	}

}
