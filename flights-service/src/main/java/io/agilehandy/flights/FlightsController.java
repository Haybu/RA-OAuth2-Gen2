package io.agilehandy.flights;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class FlightsController {

    private final FlightsRepository flightsRepository;

    public FlightsController(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    /**
    @GetMapping("/check")
    public String check(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello, %s!", jwt.getSubject());
    }
     */
    @GetMapping("/check")
    public String check(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String header = headers.entrySet().stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining("<br>"));
        return String.format("Headers: %s", header);
    }


    @GetMapping("/search/datedlegs")
    public Flux<Flight> datedFlights(@RequestParam String origin,
                                     @RequestParam String destination,
                                     @RequestParam LocalDate minDate,
                                     @RequestParam LocalDate maxDate) {
        return flightsRepository.findFlightsByCustomQueryDated(origin, destination, minDate, maxDate);

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
