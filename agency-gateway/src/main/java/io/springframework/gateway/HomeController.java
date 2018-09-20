package io.springframework.gateway;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.OAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HomeController {

    @GetMapping("/selfHome")
    public Mono<String> selfHome(@OAuth2Client OAuth2AuthorizedClient authorizedClient) {
        String name = authorizedClient.getPrincipalName();
        String client = authorizedClient.getClientRegistration().getClientName();
        return Mono.just("Hello user " + name + " with client: " + client);
    }
}
