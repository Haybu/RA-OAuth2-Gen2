package io.agilehandy.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationFailureHandler;

@Configuration
@EnableWebFluxSecurity
public class OAuth2LoginSecurityConfig {

	// @formatter:off
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return
			http
				.authorizeExchange()
				.anyExchange().authenticated()
				.and()
				.oauth2Login()
				.and()
					.formLogin()
					.loginPage("/oauth2/authorization/login-client")
					.authenticationFailureHandler(new RedirectServerAuthenticationFailureHandler("/login?error"))
				.and()
				.logout()
					.logoutUrl("http://localhost:8099/uaa/logout.do?client_id=login-client&redirect=http://localhost:8080")
				.and()
				.build()
			;
    }
    // @formatter:on


}
