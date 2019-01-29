package io.agilehandy.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebFilter;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableSpringWebSession
public class AgencyWebApplication {

	@Value("${server.context-path:/}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(AgencyWebApplication.class, args);
	}

	// set context path
	@Bean
	public WebFilter contextPathWebFilter() {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			if (request.getURI().getPath().startsWith(this.contextPath)) {
				return chain.filter(exchange.mutate()
						.request(request.mutate().contextPath(this.contextPath).build())
						.build());
			}
			return chain.filter(exchange);
		};
	}

	@Bean
	WebClient webClient(LoadBalancerExchangeFilterFunction eff,
			ReactiveClientRegistrationRepository repo1,
			ServerOAuth2AuthorizedClientRepository repo2)
	{
		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
				new ServerOAuth2AuthorizedClientExchangeFilterFunction(repo1, repo2);
		return WebClient.builder().filter(eff).filter(oauth2).build();
	}

	@Bean
	public ReactiveSessionRepository reactiveSessionRepository() {
		return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
	}

}
