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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Haytham Mohamed
 **/

@Controller
@PropertySource("classpath:messages.properties")
public class WebController {

	private final WebService webService;

	public WebController(WebService webService) {
		this.webService = webService;
	}

	@GetMapping("/")
	public String index(final Model model) {
		List<Airport> airports = webService.getAirports();
		SearchForm form = new SearchForm();
		form.setAllOrigins(airports);
		form.setAllDestinations(airports);
		model.addAttribute("searchForm", form);
		return "index";
	}

	@PostMapping("/search/flights/depart")
	public String searchDepartFlights(@ModelAttribute SearchForm searchForm, BindingResult errors, Model model) {
		Flux<Flight> flights = webService.searchDepartFlights(searchForm);
		int fluxChuncks = 1;

		ReactiveDataDriverContextVariable reactiveDataDriverContextVariable =
				new ReactiveDataDriverContextVariable(flights, fluxChuncks);

		model.addAttribute("hint", "Select Outgoing Flight");
		model.addAttribute("book", false);
		model.addAttribute("flights", reactiveDataDriverContextVariable);

//		SearchForm form = new SearchForm();
//		form.setDepartureDateSelected(searchForm.getDepartureDateSelected());
//		form.setReturnDateSelected(searchForm.getReturnDateSelected());
//		form.setOriginSelected(searchForm.getOriginSelected());
//		form.setDestinationSelected(searchForm.getDestinationSelected());
		model.addAttribute("searchForm", searchForm);

		return "result";
	}

	@PostMapping("/search/flights/return")
	public String searchReturnFlights(@ModelAttribute("searchForm") SearchForm searchForm, BindingResult errors, Model model) {
		String flightSelected = searchForm.getFlightSelected();
		searchForm.setDepartureFlightSelected(flightSelected);

		Flux<Flight> flights = webService.searchReturnFlights(searchForm);
		int fluxChuncks = 1;
		model.addAttribute("hint", "Select Returning Flight");
		model.addAttribute("book", true);
		model.addAttribute("flights",
				new ReactiveDataDriverContextVariable(flights, fluxChuncks));

//		SearchForm bounce = new SearchForm();
//		bounce.setDepartureDateSelected(searchForm.getDepartureDateSelected());
//		bounce.setReturnDateSelected(searchForm.getReturnDateSelected());
//		bounce.setOriginSelected(searchForm.getOriginSelected());
//		bounce.setDestinationSelected(searchForm.getDestinationSelected());
		model.addAttribute("searchForm", searchForm);

		return "result";
	}

	@PostMapping("/book")
	public String book(@ModelAttribute SearchForm searchForm, BindingResult errors, Model model) {
		return "confirmation";
	}

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		//The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		//Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

}
