package io.agilehandy.reservation.config;

//@Configuration
//@EnableWebFluxSecurity
public class OAuth2ResourceServerSecurityConfiguration {

    // @formatter:on
	/**
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                // becomes a client again to underneath service
                .and().and().oauth2Login()
                .and()
                ;

        return http.build();
    }
    */
    // @formatter:off

}
