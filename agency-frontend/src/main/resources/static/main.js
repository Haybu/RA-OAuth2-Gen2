(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error('Cannot find module "' + req + '".');
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule, RoutingComponents */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RoutingComponents", function() { return RoutingComponents; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _flight_search_flight_search_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./flight-search/flight-search.component */ "./src/app/flight-search/flight-search.component.ts");
/* harmony import */ var _flight_booking_flight_booking_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./flight-booking/flight-booking.component */ "./src/app/flight-booking/flight-booking.component.ts");
/* harmony import */ var _page_notfound_page_notfound_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./page-notfound/page-notfound.component */ "./src/app/page-notfound/page-notfound.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





var routes = [
    { path: '', redirectTo: '/flight-search', pathMatch: 'full' },
    { path: 'flight-search', component: _flight_search_flight_search_component__WEBPACK_IMPORTED_MODULE_2__["FlightSearchComponent"] },
    { path: 'flight-booking', component: _flight_booking_flight_booking_component__WEBPACK_IMPORTED_MODULE_3__["FlightBookingComponent"] },
    { path: '**', component: _page_notfound_page_notfound_component__WEBPACK_IMPORTED_MODULE_4__["PageNotfoundComponent"] }
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());

var RoutingComponents = [_flight_search_flight_search_component__WEBPACK_IMPORTED_MODULE_2__["FlightSearchComponent"], _flight_booking_flight_booking_component__WEBPACK_IMPORTED_MODULE_3__["FlightBookingComponent"], _page_notfound_page_notfound_component__WEBPACK_IMPORTED_MODULE_4__["PageNotfoundComponent"]];


/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!--The content below is only a placeholder and can be replaced.-->\n<div style=\"text-align:center\">\n  <h1>\n    {{ title }}\n  </h1>\n</div>\n\n<!--\n<nav class=\"navbar navbar-default\">\n  <ul class=\"nav navbar-nav\">\n    <a routerLink=\"flight-search\" routerLinkActive=\"active\"> Flight Search </a>\n    <a routerLink=\"flight-booking\" routerLinkActive=\"active\"> Flight Booking </a>\n  </ul>\n</nav>\n-->\n<div class=\"container\">\n  <router-outlet></router-outlet>\n</div>\n\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'Online Flight Booking Services';
    }
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _services_flights_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./services/flights.service */ "./src/app/services/flights.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};









var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_5__["AppComponent"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_4__["RoutingComponents"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_4__["AppRoutingModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClientModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_6__["BrowserAnimationsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatBadgeModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatFormFieldModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatDatepickerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatSelectModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatPaginatorModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatDividerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatNativeDateModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_7__["MatInputModule"]
            ],
            providers: [
                _services_flights_service__WEBPACK_IMPORTED_MODULE_8__["FlightsService"]
            ],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_5__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/flight-booking/flight-booking.component.css":
/*!*************************************************************!*\
  !*** ./src/app/flight-booking/flight-booking.component.css ***!
  \*************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/flight-booking/flight-booking.component.html":
/*!**************************************************************!*\
  !*** ./src/app/flight-booking/flight-booking.component.html ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  flight-booking works!\n</p>\n"

/***/ }),

/***/ "./src/app/flight-booking/flight-booking.component.ts":
/*!************************************************************!*\
  !*** ./src/app/flight-booking/flight-booking.component.ts ***!
  \************************************************************/
/*! exports provided: FlightBookingComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FlightBookingComponent", function() { return FlightBookingComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var FlightBookingComponent = /** @class */ (function () {
    function FlightBookingComponent() {
    }
    FlightBookingComponent.prototype.ngOnInit = function () {
    };
    FlightBookingComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-flight-booking',
            template: __webpack_require__(/*! ./flight-booking.component.html */ "./src/app/flight-booking/flight-booking.component.html"),
            styles: [__webpack_require__(/*! ./flight-booking.component.css */ "./src/app/flight-booking/flight-booking.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], FlightBookingComponent);
    return FlightBookingComponent;
}());



/***/ }),

/***/ "./src/app/flight-search/flight-search.component.css":
/*!***********************************************************!*\
  !*** ./src/app/flight-search/flight-search.component.css ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".example-container {\n  display: flex;\n  flex-direction: column;\n}\n\n.example-container > * {\n  width: 100%;\n}\n\ntable {\n  width: 100%;\n}\n\n.example-form {\n  min-width: 150px;\n  max-width: 500px;\n  width: 100%;\n}\n\n.example-full-width {\n  width: 50%;\n}\n"

/***/ }),

/***/ "./src/app/flight-search/flight-search.component.html":
/*!************************************************************!*\
  !*** ./src/app/flight-search/flight-search.component.html ***!
  \************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n\n  <!-- Search Flights -->\n\n  <div [hidden]=\"hide_search_form\" class=\"modal-body row\">\n    <div class=\"col-md-6\">\n      <div class=\"col align-items-center justify-content-center\"><h3>Flight Search Form</h3></div>\n      <div class=\"col align-items-center justify-content-center\">\n        <form (ngSubmit)=\"searchFlights()\" #flightSearchForm=\"ngForm\" class=\"example-form\">\n\n          <mat-form-field>\n            <mat-label for=\"origin\"> From </mat-label>\n            <mat-select palceholder=\"From\" id=\"origin\" name=\"origin\" [(ngModel)]=\"searchCriteria.origin\">\n              <mat-option *ngFor=\"let orig of origins\" [value]=\"orig\"> {{orig}} </mat-option>\n            </mat-select>\n          </mat-form-field>\n\n          <br/>\n\n          <mat-form-field>\n            <mat-label for=\"destination\"> To </mat-label>\n            <mat-select palceholder=\"To\" id=\"destination\" name=\"destination\" [(ngModel)]=\"searchCriteria.destination\">\n              <mat-option *ngFor=\"let dest of destinations\" [value]=\"dest\"> {{dest}} </mat-option>\n            </mat-select>\n          </mat-form-field>\n\n          <br/>\n\n          <mat-form-field>\n            <mat-label for=\"departPicker\"> Departure Date </mat-label>\n            <input matInput [matDatepicker]=\"departPicker\" placeholder=\"Departure date\" name=\"departure\" [(ngModel)]=\"searchCriteria.outDate\">\n            <mat-datepicker-toggle matSuffix [for]=\"departPicker\"></mat-datepicker-toggle>\n            <mat-datepicker #departPicker></mat-datepicker>\n          </mat-form-field>\n\n          <br/>\n\n          <mat-form-field>\n            <mat-label for=\"returnPicker\"> Return Date </mat-label>\n            <input matInput [matDatepicker]=\"returnPicker\" placeholder=\"Return date\" name=\"arrival\" [(ngModel)]=\"searchCriteria.inDate\">\n            <mat-datepicker-toggle matSuffix [for]=\"returnPicker\"></mat-datepicker-toggle>\n            <mat-datepicker #returnPicker></mat-datepicker>\n          </mat-form-field>\n\n          <br/>\n\n          <button mat-button type=\"submit\" [disabled]=\"!flightSearchForm.form.valid\"> Search </button> &nbsp;&nbsp;\n          <button mat-button type=\"button\" (click)=\"flightSearchForm.reset()\"> Reset </button>\n        </form>\n      </div>\n    </div>\n    <div class=\"col-md-6\">\n      <div class=\"col align-items-center justify-content-center\">\n        <div class=\"row\">\n          <div class=\"span4\">\n            <img style=\"float:left\" src=\"assets/images/plane-2.jpg\" class=\"img-fluid\"/>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n\n  <!-- Search Result -->\n  <div [hidden]=\"hide_select_outbound\" class=\"modal-body row\">\n    <div class=\"col-md-6\">\n      <div class=\"row align-items-center justify-content-center\"><h3>Select outbound flight</h3></div>\n      <a routerLink=\"search-flight\" class=\"btn btn-primary\">Home</a>\n      <div *ngFor=\"let f of outboundFlightsResult\" class=\"row card align-items-center\">\n        <div class=\"card-body\">\n          <h5 class=\"card-title\">{{f.airline}}</h5>\n          <h6 class=\"card-subtitle mb-2 text-muted\">Flight: {{f.nbr}}</h6>\n\n            <table class=\"table\">\n              <thead>\n              <tr>\n                <th scope=\"col\">From</th>\n                <th scope=\"col\">To</th>\n                <th scope=\"col\">Depart</th>\n                <th scope=\"col\">Arrival</th>\n                <th scope=\"col\">Price</th>\n                <th scope=\"col\"></th>\n              </tr>\n              </thead>\n              <tbody>\n              <tr>\n                <td>{{f.origin}}</td>\n                <td>{{f.destination}}</td>\n                <td>{{f.departure | date: 'MM/dd/yyyy'}}</td>\n                <td>{{f.arrival | date: 'MM/dd/yyyy'}}</td>\n                <td>{{f.price}}</td>\n                <td><a (click)=\"selectOutboundFligt(f)\" class=\"card-link\"><b>Select</b></a></td>\n              </tr>\n            </table>\n        </div>\n      </div>\n    </div>\n    <div class=\"col-md-6\">\n      <div class=\"col align-items-center justify-content-center\">\n        <div class=\"row\">\n          <div class=\"span4\">\n            <img style=\"float:left\" src=\"assets/images/plane-2.jpg\" class=\"img-fluid\"/>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n\n  <!-- returning flight -->\n  <div [hidden]=\"hide_select_inbound\" class=\"modal-body row\">\n    <div class=\"col-md-6\">\n      <div class=\"row align-items-center justify-content-center\"><h3>Select returning flight</h3></div>\n      <a routerLink=\"flight-search\" class=\"btn btn-primary\">Home</a>\n      <div *ngFor=\"let f of inboundFlightsResult\" class=\"row card align-items-center\">\n        <div class=\"card-body\">\n          <h5 class=\"card-title\">{{f.airline}}</h5>\n          <h6 class=\"card-subtitle mb-2 text-muted\">Flight: {{f.nbr}}</h6>\n\n          <table class=\"table\">\n            <thead>\n            <tr>\n              <th scope=\"col\">From</th>\n              <th scope=\"col\">To</th>\n              <th scope=\"col\">Depart</th>\n              <th scope=\"col\">Arrival</th>\n              <th scope=\"col\">Price</th>\n              <th scope=\"col\"></th>\n            </tr>\n            </thead>\n            <tbody>\n            <tr>\n              <td>{{f.origin}}</td>\n              <td>{{f.destination}}</td>\n              <td>{{f.departure | date: 'MM/dd/yyyy'}}</td>\n              <td>{{f.arrival | date: 'MM/dd/yyyy'}}</td>\n              <td>{{f.price}}</td>\n              <td><a (click)=\"selectInboundFligt(f)\" class=\"card-link\"><b>Select</b></a></td>\n            </tr>\n          </table>\n        </div>\n      </div>\n    </div>\n    <div class=\"col-md-6\">\n      <div class=\"col align-items-center justify-content-center\">\n        <div class=\"row\">\n          <div class=\"span4\">\n            <img style=\"float:left\" src=\"assets/images/plane-2.jpg\" class=\"img-fluid\"/>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n\n  <!-- passenger information -->\n  <div [hidden]=\"hide_info_passenger\" class=\"modal-body row\">\n    <div class=\"col-md-6\">\n      <div class=\"row align-items-center justify-content-center\"><h3>Enter your information</h3></div>\n      <a routerLink=\"flight-search\" class=\"btn btn-primary\">Home</a>\n      <form (ngSubmit)=\"passengerInfo()\" #passengerForm=\"ngForm\" class=\"example-form\">\n        <mat-form-field class=\"example-full-width\">\n          <input matInput placeholder=\"First Name\" value=\"First Name\" name=\"first_name\" [(ngModel)]=\"passenger.firstName\">\n        </mat-form-field>\n\n        <br/>\n\n        <mat-form-field class=\"example-full-width\">\n          <textarea matInput placeholder=\"Last Name\" name=\"last_name\" [(ngModel)]=\"passenger.lastName\"></textarea>\n        </mat-form-field>\n\n        <br/>\n\n        <mat-form-field class=\"example-full-width\">\n          <textarea matInput placeholder=\"Age\" name=\"age\" [(ngModel)]=\"passenger.age\"></textarea>\n        </mat-form-field>\n\n        <br/>\n        <button mat-button type=\"submit\" [disabled]=\"!passengerForm.form.valid\"> Next </button> &nbsp;&nbsp;\n        <button mat-button type=\"button\" (click)=\"passengerForm.reset()\"> Reset </button>\n      </form>\n    </div>\n    <div class=\"col-md-6\">\n      <div class=\"col align-items-center justify-content-center\">\n        <div class=\"row\">\n          <div class=\"span4\">\n            <img style=\"float:left\" src=\"assets/images/plane-2.jpg\" class=\"img-fluid\"/>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n\n  <!-- address information -->\n  <div [hidden]=\"hide_info_address\" class=\"modal-body row\">\n    <div class=\"col-md-6\">\n      <div class=\"row align-items-center justify-content-center\"><h3>Enter your address</h3></div>\n      <a routerLink=\"flight-search\" class=\"btn btn-primary\">Home</a>\n      <form (ngSubmit)=\"book()\" #addressForm=\"ngForm\" class=\"example-form\">\n        <mat-form-field class=\"example-full-width\">\n          <input matInput placeholder=\"address 1\" value=\"address 1\" name=\"address_1\" [(ngModel)]=\"address.address1\">\n        </mat-form-field>\n\n        <br/>\n\n        <mat-form-field class=\"example-full-width\">\n          <input matInput placeholder=\"address 2\" value=\"address 2\" name=\"address_2\" [(ngModel)]=\"address.address2\">\n        </mat-form-field>\n\n        <br/>\n\n        <mat-form-field class=\"example-full-width\">\n          <input matInput placeholder=\"city\" value=\"city\" name=\"city\" [(ngModel)]=\"address.city\">\n        </mat-form-field>\n\n        <br/>\n\n        <mat-form-field class=\"example-full-width\">\n          <input matInput placeholder=\"state\" value=\"state\" name=\"state\" [(ngModel)]=\"address.state\">\n        </mat-form-field>\n\n        <br/>\n\n        <mat-form-field class=\"example-full-width\">\n          <input matInput placeholder=\"zip\" value=\"zip\" name=\"zip\" [(ngModel)]=\"address.zip\">\n        </mat-form-field>\n\n        <br/>\n        <button mat-button type=\"submit\" [disabled]=\"!addressForm.form.valid\"> Book </button> &nbsp;&nbsp;\n        <button mat-button type=\"button\" (click)=\"addressForm.reset()\"> Reset </button>\n      </form>\n    </div>\n    <div class=\"col-md-6\">\n      <div class=\"col align-items-center justify-content-center\">\n        <div class=\"row\">\n          <div class=\"span4\">\n            <img style=\"float:left\" src=\"assets/images/plane-2.jpg\" class=\"img-fluid\"/>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n\n  <!-- confirmations -->\n  <div [hidden]=\"hide_booking\" class=\"modal-body row\">\n    <div class=\"col-md-6\">\n      <a routerLink=\"flight-search\" class=\"btn btn-primary\">Home</a>\n      <div class=\"card\">\n        <div class=\"card-body\">\n          <h5 class=\"card-title\">Outbound flight confirmation</h5>\n          <p class=\"card-text\">{{outboundConfirmation}}</p>\n        </div>\n      </div>\n      <div class=\"card\">\n        <div class=\"card-body\">\n          <h5 class=\"card-title\">Returning flight confirmation</h5>\n          <p class=\"card-text\">{{inboundConfirmation}}</p>\n        </div>\n      </div>\n      <div class=\"card\">\n        <div class=\"card-body\">\n          <h5 class=\"card-title\">Thank You</h5>\n          <p class=\"card-text\">Thanks You for using the Flight Booking Demo Application.</p>\n        </div>\n      </div>\n    </div>\n    <div class=\"col-md-6\">\n      <div class=\"col align-items-center justify-content-center\">\n        <div class=\"row\">\n          <div class=\"span4\">\n            <img style=\"float:left\" src=\"assets/images/plane-2.jpg\" class=\"img-fluid\"/>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n\n</div>\n"

/***/ }),

/***/ "./src/app/flight-search/flight-search.component.ts":
/*!**********************************************************!*\
  !*** ./src/app/flight-search/flight-search.component.ts ***!
  \**********************************************************/
/*! exports provided: FlightSearchComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FlightSearchComponent", function() { return FlightSearchComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_flights_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/flights.service */ "./src/app/services/flights.service.ts");
/* harmony import */ var _models_passenger__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../models/passenger */ "./src/app/models/passenger.ts");
/* harmony import */ var _models_address__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../models/address */ "./src/app/models/address.ts");
/* harmony import */ var _models_searchCriteria__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../models/searchCriteria */ "./src/app/models/searchCriteria.ts");
/* harmony import */ var rxjs_Observable__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs/Observable */ "./node_modules/rxjs-compat/_esm5/Observable.js");
/* harmony import */ var rxjs_add_observable_forkJoin__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! rxjs/add/observable/forkJoin */ "./node_modules/rxjs-compat/_esm5/add/observable/forkJoin.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








var FlightSearchComponent = /** @class */ (function () {
    function FlightSearchComponent(flightService, route, router) {
        this.flightService = flightService;
        this.route = route;
        this.router = router;
        this.displayedColumns = ['Airline', 'From', 'To', 'Depart', 'Return', 'Price'];
        this.origins = ['AUS', 'IAH', 'ATL', 'MHI', 'KRT'];
        this.destinations = ['AUS', 'IAH', 'ATL', 'MHI', 'KRT'];
        this.hide_search_form = false;
        this.hide_select_outbound = true;
        this.hide_select_inbound = true;
        this.hide_info_passenger = true;
        this.hide_info_address = true;
        this.hide_booking = true;
        // TODO: to be deleted
        //flight = new Flight(0, 0, '', '','',null,null,null, 0);
        this.searchCriteria = new _models_searchCriteria__WEBPACK_IMPORTED_MODULE_5__["SearchCriteria"]("", "", null, null);
        this.passenger = new _models_passenger__WEBPACK_IMPORTED_MODULE_3__["Passenger"]("", "", null);
        this.address = new _models_address__WEBPACK_IMPORTED_MODULE_4__["Address"]("", "", "", "", "", "USA");
        this.outboundFlightsResult = [];
        this.inboundFlightsResult = [];
    }
    FlightSearchComponent.prototype.ngOnInit = function () {
    };
    FlightSearchComponent.prototype.searchFlights = function () {
        var _this = this;
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
        this.flightService.searchAllFlights(this.searchCriteria.origin, this.searchCriteria.destination, this.searchCriteria.outDate, this.searchCriteria.inDate)
            .subscribe(function (data) {
            _this.outboundFlightsResult = data[0];
            _this.inboundFlightsResult = data[1];
        }, function (err) { return console.error("All flights search error: " + err.toLocaleString()); }, function () {
            console.log('All flights search is done.');
            console.log("inbound flights: " + _this.inboundFlightsResult);
            console.log("outbound flights: " + _this.outboundFlightsResult);
        });
    };
    FlightSearchComponent.prototype.selectOutboundFligt = function (f) {
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
    };
    FlightSearchComponent.prototype.selectInboundFligt = function (f) {
        this.hide_search_form = true;
        this.hide_select_outbound = true;
        this.hide_info_address = true;
        this.hide_select_inbound = true;
        this.hide_booking = true;
        this.hide_info_passenger = false;
        console.log("selected inbound fight: " + JSON.stringify(f));
        this.inboundFlight = f;
    };
    FlightSearchComponent.prototype.passengerInfo = function () {
        this.hide_search_form = true;
        this.hide_select_outbound = true;
        this.hide_info_passenger = true;
        this.hide_select_inbound = true;
        this.hide_booking = true;
        this.hide_info_address = false;
        console.log("passenger selected: " + JSON.stringify(this.passenger));
    };
    FlightSearchComponent.prototype.book = function () {
        var _this = this;
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
        rxjs_Observable__WEBPACK_IMPORTED_MODULE_6__["Observable"].forkJoin(this.flightService.book(this.outboundFlight.id, this.passenger, this.address), this.flightService.book(this.inboundFlight.id, this.passenger, this.address)).subscribe(function (data) {
            _this.outboundConfirmation = data[0].confirmation;
            _this.inboundConfirmation = data[1].confirmation;
        }, function (err) { return console.error("Error during obtaining booking confirmation: " + err.toLocaleString()); }, function () { return console.log("Finished obtaining booking confirmation successfully."); });
    };
    FlightSearchComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-flight-search',
            template: __webpack_require__(/*! ./flight-search.component.html */ "./src/app/flight-search/flight-search.component.html"),
            styles: [__webpack_require__(/*! ./flight-search.component.css */ "./src/app/flight-search/flight-search.component.css")]
        }),
        __metadata("design:paramtypes", [_services_flights_service__WEBPACK_IMPORTED_MODULE_2__["FlightsService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], FlightSearchComponent);
    return FlightSearchComponent;
}());



/***/ }),

/***/ "./src/app/models/address.ts":
/*!***********************************!*\
  !*** ./src/app/models/address.ts ***!
  \***********************************/
/*! exports provided: Address */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Address", function() { return Address; });
var Address = /** @class */ (function () {
    function Address(address1, address2, city, zip, state, country) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
    }
    return Address;
}());



/***/ }),

/***/ "./src/app/models/passenger.ts":
/*!*************************************!*\
  !*** ./src/app/models/passenger.ts ***!
  \*************************************/
/*! exports provided: Passenger */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Passenger", function() { return Passenger; });
var Passenger = /** @class */ (function () {
    function Passenger(firstName, lastName, age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    return Passenger;
}());



/***/ }),

/***/ "./src/app/models/reservationRequest.ts":
/*!**********************************************!*\
  !*** ./src/app/models/reservationRequest.ts ***!
  \**********************************************/
/*! exports provided: ReservationRequest */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReservationRequest", function() { return ReservationRequest; });
var ReservationRequest = /** @class */ (function () {
    function ReservationRequest(flightId, passengers, address, confirmation) {
        this.flightId = flightId;
        this.passengers = passengers;
        this.address = address;
        this.confirmation = confirmation;
    }
    return ReservationRequest;
}());



/***/ }),

/***/ "./src/app/models/searchCriteria.ts":
/*!******************************************!*\
  !*** ./src/app/models/searchCriteria.ts ***!
  \******************************************/
/*! exports provided: SearchCriteria */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SearchCriteria", function() { return SearchCriteria; });
var SearchCriteria = /** @class */ (function () {
    function SearchCriteria(origin, destination, outDate, inDate) {
        this.origin = origin;
        this.destination = destination;
        this.outDate = outDate;
        this.inDate = inDate;
    }
    return SearchCriteria;
}());



/***/ }),

/***/ "./src/app/page-notfound/page-notfound.component.ts":
/*!**********************************************************!*\
  !*** ./src/app/page-notfound/page-notfound.component.ts ***!
  \**********************************************************/
/*! exports provided: PageNotfoundComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PageNotfoundComponent", function() { return PageNotfoundComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var PageNotfoundComponent = /** @class */ (function () {
    function PageNotfoundComponent() {
    }
    PageNotfoundComponent.prototype.ngOnInit = function () {
    };
    PageNotfoundComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-page-notfound',
            template: "\n    <p>\n      page-notfound works!\n    </p>\n  ",
            styles: []
        }),
        __metadata("design:paramtypes", [])
    ], PageNotfoundComponent);
    return PageNotfoundComponent;
}());



/***/ }),

/***/ "./src/app/services/flights.service.ts":
/*!*********************************************!*\
  !*** ./src/app/services/flights.service.ts ***!
  \*********************************************/
/*! exports provided: FlightsService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FlightsService", function() { return FlightsService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs_Observable__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/Observable */ "./node_modules/rxjs-compat/_esm5/Observable.js");
/* harmony import */ var rxjs_add_observable_forkJoin__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/add/observable/forkJoin */ "./node_modules/rxjs-compat/_esm5/add/observable/forkJoin.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _models_reservationRequest__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../models/reservationRequest */ "./src/app/models/reservationRequest.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var FlightsService = /** @class */ (function () {
    function FlightsService(http) {
        this.http = http;
    }
    FlightsService.prototype.searchFlights = function (from, to, date) {
        var datePipe = new _angular_common__WEBPACK_IMPORTED_MODULE_4__["DatePipe"]('en-US');
        var dt = datePipe.transform(date, 'yyyy-MM-dd');
        console.log("searching: from: " + from + ", to: " + to + ", date: " + dt);
        //let url = "/api/reservations/search/"+from+"/"+to+"/"+dt;
        var url = "http://localhost:8080/api/reservations/search/" + from + "/" + to + "/" + dt;
        return this.http.get(url);
    };
    FlightsService.prototype.searchAllFlights = function (from, to, outdate, indate) {
        var datePipe = new _angular_common__WEBPACK_IMPORTED_MODULE_4__["DatePipe"]('en-US');
        var outDt = datePipe.transform(outdate, 'yyyy-MM-dd');
        var inDt = datePipe.transform(indate, 'yyyy-MM-dd');
        console.log("searching all flights");
        //let url = "/api/reservations/search/"+from+"/"+to+"/"+dt;
        var url1 = "http://localhost:8080/api/reservations/search/" + from + "/" + to + "/" + outDt;
        var url2 = "http://localhost:8080/api/reservations/search/" + to + "/" + from + "/" + inDt;
        return rxjs_Observable__WEBPACK_IMPORTED_MODULE_2__["Observable"].forkJoin(this.http.get(url2), this.http.get(url1));
        //return this.http.get<Array<Flight>>(url);
    };
    FlightsService.prototype.book = function (flightId, passenger, address) {
        //let url = "/api/reservations/book";
        var url = "http://localhost:8080/api/reservations/book";
        var passengers = new Array(passenger);
        var reservationRequest = new _models_reservationRequest__WEBPACK_IMPORTED_MODULE_5__["ReservationRequest"](flightId, passengers, address, null);
        return this.http.post(url, reservationRequest);
    };
    FlightsService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], FlightsService);
    return FlightsService;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! /Users/hmohamed/github/agilehandy.io/RA-OAuth2-Gen2/agency-ui/src/main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map