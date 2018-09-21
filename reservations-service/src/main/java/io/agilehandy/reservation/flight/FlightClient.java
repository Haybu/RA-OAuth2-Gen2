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

package io.springframework.reservation.flight;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * @author Haytham Mohamed
 */

@Component
public class FlightClient {

    private final WebClient webClient;

    public FlightClient(WebClient webClient) {
        this.webClient = webClient;
    }

	public Flux<Flight> findDatedFlights(String origin, String destination,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate mindate,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxdate)
    {
        String uri = "http://FLIGHTS-SERVICE/search/datedlegs?origin={p1}&destination={p2}&minDate={p3}&maxDate={p4}";
		return webClient
                .get()
                .uri(uri, origin, destination, mindate, maxdate)
                .retrieve()
                .bodyToFlux(Flight.class)
                ;
	}

	public Flux<Flight> findFlights(String origin, String destination) {
        String uri = "http://flights-service/search/legs?origin={p1}&destination={p2}";
        return webClient
                .get()
                .uri(uri, origin, destination)
                .retrieve()
                .bodyToFlux(Flight.class)
                ;
	}

    public Flux<Flight> findAllFlights() {
        String uri = "http://flights-service";
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Flight.class)
                ;
    }

	public Mono<Flight> findById(String id) {
        String uri = "http://FLIGHTS-SERVICE/{id}";
        return webClient
                .get()
                .uri(uri, id)
                .retrieve()
                .bodyToMono(Flight.class)
                ;
	}

	public Mono<Void> update(Flight flight) {
        String uri = "http://FLIGHTS-SERVICE";
        return webClient
                .post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(flight)
                .retrieve()
                .bodyToMono(Void.class)
                ;
	}

	public Flux<String> origins() {
        String uri = "http://FLIGHTS-SERVICE/origins";
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(String.class)
                ;
	}

	public Flux<String> destinations() {
        String uri = "http://FLIGHTS-SERVICE/destinations";
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(String.class)
                ;
	}

}
