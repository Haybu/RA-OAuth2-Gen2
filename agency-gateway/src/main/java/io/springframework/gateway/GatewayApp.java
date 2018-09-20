package io.springframework.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class GatewayApp {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApp.class, args);
	}

	@Bean
	public RouteLocator routeLocator (RouteLocatorBuilder builder) {
		return builder
                .routes()
				.route( r -> r.path("/api/flights/**")
                        .filters(f -> {
                            f.rewritePath("/api/flights/(?<segment>.*)", "/flights/$\\{segment}");
                            return f;
                        })
						.uri("lb://FLIGHTS-SERVICE")
				)
                .route( r -> r.path("/api/reservations/**")
                        .filters(f -> {
                            f.rewritePath("/api/reservations/(?<segment>.*)", "/reservations/$\\{segment}");
                            return f;
                        })
                        .uri("lb://RESERVATIONS-SERVICE")
                )
                .route( r -> r.path("/api/self/**")
                        .filters(f -> {
                            f.rewritePath("/api/self/(?<segment>.*)", "/self/$\\{segment}");
                            return f;
                        })
                        .uri("forward:///selfHome")
                )
				.route( r -> r.path("/**")
						.uri("lb://AGENCY-FRONTEND")
				)
				.build();
	}

}
