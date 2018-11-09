package io.agilehandy.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

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
	public GlobalFilter globalFilter(ReactiveOAuth2AuthorizedClientService service) {
		return (exchange,chain) -> ReactiveSecurityContextHolder.getContext()
				.map(securityContext -> securityContext.getAuthentication())
				.map(authentication -> (OAuth2AuthenticationToken) authentication)
				.flatMap(oAuth2Authentication -> {
					String clientId = oAuth2Authentication.getAuthorizedClientRegistrationId();
					OAuth2User user = oAuth2Authentication.getPrincipal();
					return service.loadAuthorizedClient(clientId, user.getName());
				})
				.map(authorizedClient -> ((OAuth2AuthorizedClient)authorizedClient).getAccessToken())
				.map(accessToken -> accessToken.getTokenValue())
				.log()
				.flatMap(bearerToken -> {
					ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
					builder.header(HttpHeaders.AUTHORIZATION,"Bearer "+bearerToken);
					ServerHttpRequest request = builder.build();
					return chain.filter(exchange.mutate().request(request).build());
				});
	}

}
