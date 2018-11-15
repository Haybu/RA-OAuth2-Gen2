package io.agilehandy.reservation.flight;

import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;


/**
 * @author Haytham Mohamed
 */

@Component
public class FlightClient {

    private final WebClient webClient;

    public FlightClient(WebClient webClient) {
        this.webClient = webClient;
    }

	public Mono<Flight> findById(String id, final OAuth2AuthorizedClient oauth2Client) {
        String uri = "http://FLIGHTS-SERVICE/{id}";
        return webClient
                .get()
                .uri(uri, id)
		        .attributes(oauth2AuthorizedClient(oauth2Client))
                .retrieve()
                .bodyToMono(Flight.class)
                ;
	}

	public Mono<Void> update(Flight flight, final OAuth2AuthorizedClient oauth2Client) {
        String uri = "http://FLIGHTS-SERVICE";
        return webClient
                .post()
                .uri(uri)
		        .attributes(oauth2AuthorizedClient(oauth2Client))
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(flight)
                .retrieve()
                .bodyToMono(Void.class)
                ;
	}

}
