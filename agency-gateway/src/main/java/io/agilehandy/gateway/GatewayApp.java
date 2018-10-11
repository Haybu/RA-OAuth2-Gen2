package io.agilehandy.gateway;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.server.ServerWebExchange;

@SpringBootApplication
@Slf4j
public class GatewayApp {

    private static final String BEARER = "bearer ";

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
                        .uri("forward:///self")
                )
				.route( r -> r.path("/**")
						.uri("lb://AGENCY-FRONTEND")
				)
				.build();
	}

    @Bean
    @Order(-1)
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            String token = this.extractToken(exchange);
            ServerHttpRequest.Builder requestBuilder = exchange.getRequest().mutate();
            if (token != null) {
                requestBuilder.headers((headers) ->
                        headers.set(HttpHeaders.AUTHORIZATION, BEARER + token));
            } else {
                log.info("No bearer token header to add");
            }
            return chain.filter(exchange.mutate().request(requestBuilder.build()).build());
        };
    }

    private String extractToken(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(token) || !token.toLowerCase().startsWith(BEARER)) {
            return null;
        }
        return token.substring(BEARER.length());
    }

}
