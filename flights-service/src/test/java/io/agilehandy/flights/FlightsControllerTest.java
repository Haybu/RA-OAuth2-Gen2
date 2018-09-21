package io.agilehandy.flights;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest
@TestPropertySource(properties = {
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false",
        "spring.cloud.config.enabled=false",
        "data.filepath=/flights.dat"
})
@ActiveProfiles("test")
public class FlightsControllerTest {

    @MockBean
    private FlightsRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    Flight flight;
    LocalDate departureDate = LocalDate.now();
    ObjectId id = new ObjectId();

    @Before
    public void setUp() {
        flight = new Flight();
        flight.setId(id.toHexString());
        flight.setAirline("American");
        flight.setCapacity(200);
        flight.setOrigin("X");
        flight.setDestination("Y");
        flight.setNbr("FL000");
        flight.setDeparture(departureDate);
    }

    @Test
    public void getFlightById_returnsFlight() throws Exception {
        given(repository.findById(id)).willReturn(Mono.just(flight));

        this.webTestClient.get().
                uri("/{id}",
                id.toHexString())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.airline").isEqualTo("American")
                .jsonPath("$.capacity").isEqualTo(200)
        ;
    }

    @Test
    public void getFlightsByParams_returnsFlights() throws Exception {
        given(repository.findFlightsByCustomQueryDated(anyString(), anyString(), any(), any()))
                .willReturn(Flux.just(flight));

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

}
