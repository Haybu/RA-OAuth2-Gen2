package io.springframework.reservation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebFilter;

@SpringBootApplication
//@EnableCircuitBreaker
public class ReservationServiceApp {

	@Value("${server.context-path:/}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApp.class, args);
	}


	@Bean
	WebClient client(LoadBalancerExchangeFilterFunction eff) {
		return WebClient.builder().filter(eff).build();
	}

	// set context path
	@Bean
	public WebFilter contextPathWebFilter() {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			if (request.getURI().getPath().startsWith(this.contextPath)) {
				return chain.filter(
						exchange.mutate()
								.request(request.mutate().contextPath(this.contextPath).build())
								.build());
			}
			return chain.filter(exchange);
		};
	}

}
