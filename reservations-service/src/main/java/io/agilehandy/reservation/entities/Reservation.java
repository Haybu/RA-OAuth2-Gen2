/*
 * Copyright 2012-2016 the original author or authors.
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

package io.agilehandy.reservation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Haytham Mohamed
 */

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {

	@Id
	private ObjectId id;

	private String confirmationNumber;

	private String origin;

	private String destination;

	private LocalDate departure;

	//private LocalDate arrival;

	private String flightNumber;

	//private Long flightId;

	private List<Passenger> passengers;

	public String getId() {
		return id.toString();
	}
	public void setId(String id) {
		this.id = new ObjectId(id);
	}

}
