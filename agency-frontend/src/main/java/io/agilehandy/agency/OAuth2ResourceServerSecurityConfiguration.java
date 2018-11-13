package io.agilehandy.agency;

//@Configuration
//@EnableWebFluxSecurity
public class OAuth2ResourceServerSecurityConfiguration {

    // @formatter:on
    /**
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                //.pathMatchers("/css/**","/js/**","/resource/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .cors().and().csrf().disable()
                .oauth2ResourceServer()
                .jwt()
                ;
        return http.build();
    }
    // @formatter:off

    @Bean
    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    */

}