package io.springframework.reservation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Passenger {

	public static enum Gender {

		MALE, FEMALE

	}

	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfBirth;
	private int age;
	private Gender gender;
	private Address address;
	private Reservation reservation;
}
