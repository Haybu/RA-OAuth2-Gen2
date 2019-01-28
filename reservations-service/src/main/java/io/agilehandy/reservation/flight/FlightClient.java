package io.agilehandy.reservation.flight;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

/**
 * @author Haytham Mohamed
 */

@Component
public class FlightClient {

	private final WebClient webClient;

	public FlightClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<Flight> findById(String id) {
		String uri = "http://FLIGHTS-SERVICE/{id}";
		// @formatter:off
        return webClient
                .get()
                .uri(uri, id)
		        .attributes(clientRegistrationId("client-book"))
                .retrieve()
                .bodyToMono(Flight.class)
                ;
        // @formatter:on
	}

	public Mono<Void> update(Flight flight) {
		String uri = "http://FLIGHTS-SERVICE";
		// @formatter:off
        return webClient
                .post()
                .uri(uri)
		        .attributes(clientRegistrationId("client-book"))
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(flight)
                .retrieve()
                .bodyToMono(Void.class)
                ;
		// @formatter:on
	}

}
