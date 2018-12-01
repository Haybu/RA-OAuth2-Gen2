/*
 * Copyright 2017 the original author or authors.
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

package io.agilehandy.ui.client;

import io.agilehandy.ui.model.Flight;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

/**
 * @author Haytham Mohamed
 **/
@Component
public class FlightsClient {

	private final String GATEWAY_URL = "http://agency-gateway/api/flights";

	private final WebClient webClient;

	public FlightsClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Flux<Flight> findDatedFlights(String origin, String destination,
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate mindate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxdate) {
		String uri = GATEWAY_URL
				+ "/search/datedlegs?origin={p1}&destination={p2}&minDate={p3}&maxDate={p4}";
		return webClient.get().uri(uri, origin, destination, mindate, maxdate)
				.attributes(clientRegistrationId("client-search"))
				.retrieve()
				.bodyToFlux(Flight.class);
	}

	public Flux<Flight> findFlights(String origin, String destination) {
		String uri = GATEWAY_URL + "/search/legs?origin={p1}&destination={p2}";
		return webClient.get().uri(uri, origin, destination)
				.attributes(clientRegistrationId("client-search"))
				.retrieve()
				.bodyToFlux(Flight.class);
	}

	// all below methods are currently not in use

	public Flux<Flight> findAllFlights() {
		String uri = GATEWAY_URL;
		return webClient.get().uri(uri).attributes(clientRegistrationId("client-search"))
				.retrieve().bodyToFlux(Flight.class);
	}

	public Mono<Flight> findById(String id) {
		String uri = GATEWAY_URL + "/{id}";
		return webClient.get().uri(uri, id)
				.attributes(clientRegistrationId("client-search"))
				.retrieve()
				.bodyToMono(Flight.class);
	}

	public Flux<String> allOrigins() {
		String uri = GATEWAY_URL + "/origins";
		return webClient.get().uri(uri).attributes(clientRegistrationId("client-search"))
				.retrieve().bodyToFlux(String.class);
	}

	public Flux<String> allDestinations() {
		String uri = GATEWAY_URL + "/destinations";
		return webClient.get().uri(uri).attributes(clientRegistrationId("client-search"))
				.retrieve().bodyToFlux(String.class);
	}

}
