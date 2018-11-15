package io.agilehandy.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class OAuth2ResourceServerSecurityConfiguration {

    // @formatter:off
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
		        .csrf().disable()
                .oauth2ResourceServer()
                .jwt()
                // becomes a client again to underneath service
                .and().and().oauth2Login()
                .and()
                ;

        return http.build();
    }
    // @formatter:on

}
