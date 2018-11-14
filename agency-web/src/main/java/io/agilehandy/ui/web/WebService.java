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

import io.agilehandy.ui.model.Airport;
import io.agilehandy.ui.model.Flight;
import io.agilehandy.ui.model.SearchForm;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * @author Haytham Mohamed
 **/
@Service
public class WebService {

	public Flux<Flight> searchDepartFlights(final SearchForm form) {

		System.out.println(form);

		Flight f1 = new Flight();
		f1.setAirline("Delta");
		f1.setOrigin("AUS");
		f1.setDestination("IAH");
		f1.setId("1");
		return Flux.just(f1);
	}

	public Flux<Flight> searchReturnFlights(final SearchForm form) {

		System.out.println(form);

		Flight f1 = new Flight();
		f1.setAirline("American");
		f1.setOrigin("IAH");
		f1.setDestination("AUS");
		f1.setId("2");
		return Flux.just(f1);
	}

	public List<Airport> getAirports() {
		return Arrays.asList(
				new Airport("AUS", "AUS"), new Airport("IAH", "IAH"),
				new Airport("ATL", "ATL"), new Airport("MHI", "MNI"));
	}
}
