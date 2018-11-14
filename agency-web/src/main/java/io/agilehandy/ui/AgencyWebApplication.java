package io.agilehandy.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebFilter;

@SpringBootApplication
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
				return chain.filter(
						exchange.mutate()
								.request(request.mutate().contextPath(this.contextPath).build())
								.build());
			}
			return chain.filter(exchange);
		};
	}

	@Bean
	public WebClient webClient(LoadBalancerExchangeFilterFunction eff){
		return WebClient.builder()
				//.filter(eff)
				.build();
	}
}
