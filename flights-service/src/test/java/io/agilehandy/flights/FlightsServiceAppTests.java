package io.agilehandy.flights;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false",
        "spring.cloud.config.enabled=false",
        "data.filepath=/flights.dat"
})
public class FlightsServiceAppTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private FlightsRepository flightsRepository;

    Flight flight;
    LocalDate departureDate = LocalDate.now();

	@Before
    public void setUp() {
        flight = new Flight();
        flight.setAirline("American");
        flight.setCapacity(200);
        flight.setOrigin("X");
        flight.setDestination("Y");
        flight.setNbr("FL000");
        flight.setDeparture(departureDate);

        this.flightsRepository.save(flight)
                .then()
                .block();
    }

    @Test
    public void searchFlight_ReturnsFlight() {
        LocalDate mindt = departureDate.minusDays(3);
        LocalDate maxdt = departureDate.plusDays(3);

        this.webTestClient.get()
                .uri("/search/datedlegs?origin={p1}&destination={p2}&minDate={p3}&maxDate={p4}",
                        "X","Y", mindt, maxdt)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].airline").isEqualTo("American")
                 .jsonPath("$.[0].capacity").isEqualTo(200)
                ;
    }

	@Test
	public void contextLoads() {
	}

}
