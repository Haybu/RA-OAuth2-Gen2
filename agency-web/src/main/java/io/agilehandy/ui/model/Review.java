package io.agilehandy.ui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {

	private String departureFlightId;

	private String returnFlightId;

	//private List<Passenger> passengers;

	//private Address address;

	//private String confirmation;

}
