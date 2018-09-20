import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FlightSearchComponent} from "./flight-search/flight-search.component";
import {FlightBookingComponent} from "./flight-booking/flight-booking.component";
import {PageNotfoundComponent} from "./page-notfound/page-notfound.component";

const routes: Routes = [
  {path: '', redirectTo: '/flight-search', pathMatch: 'full'},
  {path: 'flight-search', component: FlightSearchComponent},
  {path: 'flight-booking', component: FlightBookingComponent},
  {path: '**', component: PageNotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const RoutingComponents = [FlightSearchComponent, FlightBookingComponent, PageNotfoundComponent]
