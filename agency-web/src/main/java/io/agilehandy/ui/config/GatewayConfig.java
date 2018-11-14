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


package io.agilehandy.ui.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Haytham Mohamed
 **/
@Configuration
public class GatewayConfig {

	private static final String BEARER = "bearer ";

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
