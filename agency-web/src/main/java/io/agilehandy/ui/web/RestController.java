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


package io.agilehandy.ui.web;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * @author Haytham Mohamed
 **/

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private final WebService webService;

	public RestController(WebService webService) {
		this.webService = webService;
	}

	@GetMapping("/flights/ping")
	public Mono<String> pingFlightsService(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient oauth2Client) {
		return webService.pingFlightsService(oauth2Client);
	}
}
