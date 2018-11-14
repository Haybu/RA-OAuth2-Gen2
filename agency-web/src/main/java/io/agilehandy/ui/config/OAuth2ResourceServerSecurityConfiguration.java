package io.agilehandy.ui.config;

//@Configuration
//@EnableWebFluxSecurity
public class OAuth2ResourceServerSecurityConfiguration {

    // @formatter:on
	/**
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/**").permitAll() // disable security for now
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                ;
        return http.build();
    }
    */
    // @formatter:off

}
