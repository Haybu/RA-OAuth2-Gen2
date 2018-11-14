package io.agilehandy.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

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
						.uri("lb://flights-service")
				)
                .route( r -> r.path("/api/reservations/**")
                        .filters(f -> {
                            f.rewritePath("/api/reservations/(?<segment>.*)", "/reservations/$\\{segment}");
                            return f;
                        })
                        .uri("lb://reservations-service")
                )
				.route( r -> r.path("/**")
						.uri("lb://agency-web")
				)
				.build();
	}

	/**
	 * a global gateway filter to pass a bearer token on a header for any proxied service.
	 * The bearer token would be added to the request's headers.
	 * @param service
	 * @return
	 */
	/**
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
	*/

}
