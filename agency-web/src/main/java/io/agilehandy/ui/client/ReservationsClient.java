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


package io.agilehandy.ui.client;

import io.agilehandy.ui.model.ReservationRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Haytham Mohamed
 **/

@Component
public class ReservationsClient {

	private final String GATEWAY_URL = "http://agency-gateway/api/reservations";

	private final WebClient webClient;

	public ReservationsClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<ReservationRequest> book(ReservationRequest request) {
		String uri = GATEWAY_URL + "/book";
		return webClient
				.post()
				.uri(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.syncBody(request)
				.retrieve()
				.bodyToMono(ReservationRequest.class)
				;
	}
}
