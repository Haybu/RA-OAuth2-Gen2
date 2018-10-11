package io.agilehandy.flights.config;

//@EnableWebFluxSecurity
public class OAuth2ResourceServerSecurityConfiguration {

    /**
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                ;
        return http.build();
    }
    */

}
