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

import io.agilehandy.ui.client.FlightsClient;
import io.agilehandy.ui.client.ReservationsClient;
import io.agilehandy.ui.model.Airport;
import io.agilehandy.ui.model.Flight;
import io.agilehandy.ui.model.ReservationRequest;
import io.agilehandy.ui.model.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author Haytham Mohamed
 **/
@Service
@Slf4j
public class WebService {

	private final FlightsClient flightsClient;

	private final ReservationsClient reservationsClient;

	public WebService(FlightsClient flightsClient,
			ReservationsClient reservationsClient) {
		this.flightsClient = flightsClient;
		this.reservationsClient = reservationsClient;
	}

	public Flux<Flight> searchDepartFlights(final SearchForm form,
			final OAuth2AuthorizedClient oauth2Client) {
		System.out.println("WebService::searchDepartFlights: searching depart flights");
		LocalDate date = form.getDepartureDateSelected();
		LocalDate min_date = date.minusDays(3);
		LocalDate max_date = date.plusDays(3);
		return flightsClient.findDatedFlights(form.getOriginSelected(),
				form.getDestinationSelected(), min_date, max_date, oauth2Client);
	}

	public Flux<Flight> searchReturnFlights(final SearchForm form,
			final OAuth2AuthorizedClient oauth2Client) {
		LocalDate date = form.getReturnDateSelected();
		LocalDate min_date = date.minusDays(3);
		LocalDate max_date = date.plusDays(3);
		return flightsClient.findDatedFlights(form.getDestinationSelected(),
				form.getOriginSelected(), min_date, max_date, oauth2Client);
	}

	public Mono<Flight> getFlightById(String flightId,
			final OAuth2AuthorizedClient oauth2Client) {
		return flightsClient.findById(flightId, oauth2Client);
	}

	public List<Airport> getAirports(final OAuth2AuthorizedClient oauth2Client) {
		return Arrays.asList(new Airport("AUS", "AUS"), new Airport("IAH", "IAH"),
				new Airport("ATL", "ATL"), new Airport("MHI", "MNI"));
	}

	public Flux<String> allOrigins(final OAuth2AuthorizedClient oauth2Client) {
		return this.flightsClient.allOrigins(oauth2Client);
	}

	public Flux<String> allDestinations(final OAuth2AuthorizedClient oauth2Client) {
		return this.flightsClient.allDestinations(oauth2Client);
	}

	public Mono<ReservationRequest> book(ReservationRequest request,
			final OAuth2AuthorizedClient oauth2Client) {
		return reservationsClient.book(request, oauth2Client);
	}

}
