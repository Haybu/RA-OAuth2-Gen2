package io.agilehandy.reservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAuthority;

@Configuration
@EnableWebFluxSecurity
public class OAuth2SecurityConfiguration {

	@Autowired
	private OAuth2ResourceServerProperties resourceServerProperties;

	// @formatter:off
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http
			.authorizeExchange()
			.pathMatchers(HttpMethod.POST)
				.access(hasAuthority("SCOPE_reserve"))
			.anyExchange().authenticated().and()
			.oauth2ResourceServer()
				.jwt()
					.jwkSetUri(this.resourceServerProperties.getJwt().getJwkSetUri())
			// a client for underneath flights service
			.and().and().oauth2Client();

		return http.build();
	}
	// @formatter:on

}
