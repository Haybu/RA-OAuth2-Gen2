import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import {FlightsService} from "../services/flights.service";

import {Flight} from "../models/flight";
import {Passenger} from "../models/passenger";
import {Address} from "../models/address";
import {SearchCriteria} from "../models/searchCriteria";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/forkJoin';

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent implements OnInit {

  displayedColumns = ['Airline', 'From', 'To', 'Depart', 'Return', 'Price'];

  origins = ['AUS', 'IAH', 'ATL', 'MHI', 'KRT'];
  destinations = ['AUS', 'IAH', 'ATL', 'MHI', 'KRT'];

  hide_search_form = false;
  hide_select_outbound = true;
  hide_select_inbound = true;
  hide_info_passenger = true;
  hide_info_address = true;
  hide_booking = true;

  // TODO: to be deleted
  //flight = new Flight(0, 0, '', '','',null,null,null, 0);
  searchCriteria = new SearchCriteria("", "", null, null);
  passenger : Passenger = new Passenger("", "", null);
  address : Address = new Address("", "", "", "", "", "USA");

  outboundFlight: Flight;
  inboundFlight: Flight;
  outboundFlightsResult: Array<Flight> = [];
  inboundFlightsResult: Array<Flight> = [];
  outboundConfirmation: string;
  inboundConfirmation: string;

  constructor(
    private flightService: FlightsService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
  }

  searchFlights() {
    this.hide_search_form = true;
    this.hide_info_passenger = true;
    this.hide_info_address = true;
    this.hide_select_inbound = true;
    this.hide_booking = true;
    this.hide_select_outbound = false;

    /**
    this.flightService.searchFlights(this.searchCriteria.origin, this.searchCriteria.destination, this.searchCriteria.outDate)
      .subscribe(data => {
          this.outboundFlightsResult = data;
        },
        err => console.error("Outbound flight search error: " + err.toLocaleString()),
        () => {
          console.log('Outbound flights search is done.');
          console.log(this.outboundFlightsResult);
        }
      );

    this.flightService.searchFlights(this.searchCriteria.destination, this.searchCriteria.origin, this.searchCriteria.inDate)
      .subscribe(data => {
          this.inboundFlightsResult = data;
        },
        err => console.error("Inbound flight search error: " + err),
        () => {
          console.log('Inbound flights search is done.');
          console.log(this.inboundFlightsResult);
        }
      );
     */

    this.flightService.searchAllFlights(this.searchCriteria.origin, this.searchCriteria.destination
          , this.searchCriteria.outDate, this.searchCriteria.inDate)
      .subscribe( data => {
          this.outboundFlightsResult = data[0];
          this.inboundFlightsResult = data[1];
        },
        err => console.error("All flights search error: " + err.toLocaleString()),
        () => {
          console.log('All flights search is done.');
          console.log("inbound flights: " + this.inboundFlightsResult);
          console.log("outbound flights: " + this.outboundFlightsResult);
        }
      );

  }

  selectOutboundFligt(f: Flight) {
    this.hide_search_form = true;
    this.hide_select_outbound = true;
    this.hide_info_passenger = true;
    this.hide_info_address = true;
    this.hide_booking = true;
    this.hide_select_inbound = false;

    console.log("selected outbound fight: " + JSON.stringify(f));
    this.outboundFlight = f;

    // retrieve inbound flights
    /**
    this.flightService.searchFlights(this.searchCriteria.destination, this.searchCriteria.origin, this.searchCriteria.inDate)
      .subscribe(data => {
          this.inboundFlightsResult = data;
        },
        err => console.error("Inbound flight search error: " + err.toLocaleString()),
        () => {
          console.log('Inbound flights search is done.');
          console.log(this.inboundFlightsResult);
        }
      );
     */
  }

  selectInboundFligt(f: Flight) {
    this.hide_search_form = true;
    this.hide_select_outbound = true;
    this.hide_info_address = true;
    this.hide_select_inbound = true;
    this.hide_booking = true;
    this.hide_info_passenger = false;

    console.log("selected inbound fight: " + JSON.stringify(f));
    this.inboundFlight = f;
  }

  passengerInfo() {
    this.hide_search_form = true;
    this.hide_select_outbound = true;
    this.hide_info_passenger = true;
    this.hide_select_inbound = true;
    this.hide_booking = true;
    this.hide_info_address = false;

    console.log("passenger selected: " + JSON.stringify(this.passenger));
  }

  book() {
    this.hide_search_form = true;
    this.hide_select_outbound = true;
    this.hide_info_passenger = true;
    this.hide_select_inbound = true;
    this.hide_info_address = true;
    this.hide_booking = false;
    console.log("address selected: " + JSON.stringify(this.address));

    /**
    this.flightService.book(this.outboundFlight.id, this.passenger, this.address).subscribe(data => {
      this.outboundConfirmation = data.confirmation;
    });

    this.flightService.book(this.inboundFlight.id, this.passenger, this.address).subscribe(data => {
      this.inboundConfirmation = data.confirmation;
    });
     */

    Observable.forkJoin(
      this.flightService.book(this.outboundFlight.id, this.passenger, this.address),
      this.flightService.book(this.inboundFlight.id, this.passenger, this.address)
    ).subscribe(data => {
        this.outboundConfirmation = data[0].confirmation;
        this.inboundConfirmation = data[1].confirmation;
      },
      err => console.error("Error during obtaining booking confirmation: " + err.toLocaleString()),
      () => console.log("Finished obtaining booking confirmation successfully.")
    );
  }

}
