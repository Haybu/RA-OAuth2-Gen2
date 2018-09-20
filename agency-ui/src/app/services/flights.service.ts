import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Flight} from "../models/flight";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/forkJoin';
import {DatePipe} from "@angular/common";
import {Passenger} from "../models/passenger";
import {Address} from "../models/address";
import {ReservationRequest} from "../models/reservationRequest";

@Injectable({
  providedIn: 'root'
})
export class FlightsService {

  constructor(private http: HttpClient) { }

  searchFlights(from: string, to: string, date: Date): Observable<Array<Flight>> {
    var datePipe = new DatePipe('en-US');
    let dt = datePipe.transform(date, 'yyyy-MM-dd');
    console.log("searching: from: " + from + ", to: " + to + ", date: " + dt);
    //let url = "/api/reservations/search/"+from+"/"+to+"/"+dt;
    let url = "http://localhost:8080/api/reservations/search/"+from+"/"+to+"/"+dt;
    return this.http.get<Array<Flight>>(url);
  }

  searchAllFlights(from: string, to: string, outdate: Date, indate: Date): Observable<Array<Flight>[]>{
    var datePipe = new DatePipe('en-US');
    let outDt = datePipe.transform(outdate, 'yyyy-MM-dd');
    let inDt = datePipe.transform(indate, 'yyyy-MM-dd');
    console.log("searching all flights");
    //let url = "/api/reservations/search/"+from+"/"+to+"/"+dt;
    let url1 = "http://localhost:8080/api/reservations/search/"+from+"/"+to+"/"+outDt;
    let url2 = "http://localhost:8080/api/reservations/search/"+to+"/"+from+"/"+inDt;

    return Observable.forkJoin(
              this.http.get<Array<Flight>>(url2),
              this.http.get<Array<Flight>>(url1)
              );
    //return this.http.get<Array<Flight>>(url);
  }

  book(flightId: number, passenger: Passenger, address: Address): Observable<ReservationRequest> {
    //let url = "/api/reservations/book";
    let url = "http://localhost:8080/api/reservations/book";
    let passengers = new Array(passenger);
    let reservationRequest = new ReservationRequest(flightId, passengers, address, null);
    return this.http.post<ReservationRequest>(url, reservationRequest);
  }

}
