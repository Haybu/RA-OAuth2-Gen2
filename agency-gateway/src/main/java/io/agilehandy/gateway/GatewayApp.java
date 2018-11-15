package io.agilehandy.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

@SpringBootApplication
@Slf4j
public class GatewayApp {

	private static final String BEARER = "bearer ";

	public static void main(String[] args) {
		SpringApplication.run(GatewayApp.class, args);
	}

	// @formatter:off
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
            .route(r -> r.path("/api/flights/**")
		            .filters(f -> {
		            	f.rewritePath("/api/flights/(?<segment>.*)",
					            "/flights/$\\{segment}");
			             return f;
		            })
                   .uri("lb://flights-service"))
            .route(r -> r.path("/api/reservations/**")
		             .filters(f -> {
					     f.rewritePath("/api/reservations/(?<segment>.*)",
							"/reservations/$\\{segment}");
					     return f;
				     })
                   .uri("lb://reservations-service")).build();
	}
	// @formatter:on

	/**
	 * a global gateway filter to pass a bearer token on a header for any proxied service.
	 * The bearer token would be added to the request's headers.
	 * @return GlobalFilter
	 */
	// @formatter:off
	@Bean
	public GlobalFilter globalFilter() {
		return (exchange, chain) -> exchange.getRequest().getHeaders()
				.entrySet()
				.stream()
				.filter(entry -> entry.getKey().equals(HttpHeaders.AUTHORIZATION))
				.map(header -> header.getValue())
				.map(bearerToken -> {
					ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
					builder.header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
					return builder.build();
				})
				.map(request -> chain.filter(exchange.mutate().request(request).build()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Error creating the global filter"))
				;
	}
	// @formatter:on

}
