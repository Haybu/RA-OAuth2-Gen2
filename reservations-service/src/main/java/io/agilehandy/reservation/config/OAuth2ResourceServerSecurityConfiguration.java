package io.agilehandy.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class OAuth2ResourceServerSecurityConfiguration {

    // @formatter:on
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                // becomes a client again to underneath service
                .and().and().oauth2Login().and()
                ;

        return http.build();
    }
    // @formatter:off

}
