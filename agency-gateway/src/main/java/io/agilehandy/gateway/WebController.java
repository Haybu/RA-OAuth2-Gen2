package io.agilehandy.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@Slf4j
public class WebController {

    @GetMapping("/self")
    public Mono<String> self(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                             @AuthenticationPrincipal OAuth2User oauth2User,
                             ServerWebExchange exchange) {
        this.printHeaders(exchange);////////
        Mono<Principal> principal = exchange.getPrincipal();
        principal.doOnNext(p -> System.out.print(">>>> " + p.getClass().getTypeName()));
        String token = authorizedClient.getAccessToken().getTokenValue();
        return Mono.just("Hello from Spring Cloud Gateway!\n token = " + token);
    }

    private void printHeaders(ServerWebExchange exchange) {
        exchange.getRequest().getHeaders().entrySet()
                .stream()
                .forEach(e -> log.info("Header " + e.getKey() + " = " + e.getValue() + "\n"));
    }

}
