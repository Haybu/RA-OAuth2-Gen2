package io.agilehandy.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Review {

	private String departureFlightId;

	private String returnFlightId;

	private List<Passenger> passengers;

	private Address address;

	private String confirmation;

}
