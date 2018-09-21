package io.agilehandy.reservation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

	private String address1;
	private String address2;
	private String zipCode;
	private String state;
	private String country;
	private Passenger passenger;
}
