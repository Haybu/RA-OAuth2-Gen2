package io.agilehandy.reservation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebFilter;

@SpringBootApplication
// @EnableCircuitBreaker
public class ReservationServiceApp {

	@Value("${server.context-path:/}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApp.class, args);
	}

	@Bean
	@Profile("!test")
	WebClient webClient(LoadBalancerExchangeFilterFunction eff,
			ReactiveClientRegistrationRepository repo1,
			ServerOAuth2AuthorizedClientRepository repo2) {

		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
				repo1, repo2);

		// @formatter:off
		return WebClient.builder()
                .filter(eff)
				.filter(oauth2)
                .build();
		// @formatter:on
	}

	// set context path
	@Bean
	public WebFilter contextPathWebFilter() {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			if (request.getURI().getPath().startsWith(this.contextPath)) {
				return chain.filter(exchange.mutate()
						.request(request.mutate().contextPath(this.contextPath).build())
						.build());
			}
			return chain.filter(exchange);
		};
	}

}
