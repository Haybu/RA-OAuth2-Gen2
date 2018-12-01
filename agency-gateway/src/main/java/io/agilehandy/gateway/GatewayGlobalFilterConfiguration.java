/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.agilehandy.gateway;

/**
 * @author Haytham Mohamed
 **/
//@Configuration
public class GatewayGlobalFilterConfiguration {

	private static final String BEARER = "Bearer ";

	/**
	 * a global gateway filter to pass a bearer token on a header for any proxied service.
	 * The bearer token would be added to the request's headers.
	 * @return GlobalFilter
	 */
	// @formatter:off
	/**
	@Bean
	public GlobalFilter globalFilter() {
		return (exchange, chain) -> exchange.getRequest().getHeaders()
				.entrySet()
				.stream()
				.filter(entry -> entry.getKey().equals(HttpHeaders.AUTHORIZATION))
				.map(header -> header.getValue())
				.map(bearerToken -> {
					ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
					builder.header(HttpHeaders.AUTHORIZATION, BEARER + bearerToken);
					return builder.build();
				})
				.map(request -> chain.filter(exchange.mutate().request(request).build()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Error creating the global filter"))
				;
	}
	*/
	// @formatter:on

}
