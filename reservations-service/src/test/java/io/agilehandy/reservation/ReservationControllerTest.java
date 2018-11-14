package io.agilehandy.reservation;

import io.agilehandy.reservation.flight.Flight;
import org.bson.types.ObjectId;
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
        "server.context-path=/reservations"
})
@ActiveProfiles("test")
public class ReservationControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    ReservationService reservationService;

    @Test
    public void searchFlight_shouldReturnFlight() throws Exception {
        LocalDate departureDate = LocalDate.now();
        ObjectId id = new ObjectId();

        Flight flight = new Flight();
        flight.setId(id.toHexString());
        flight.setAirline("Delta");
        flight.setCapacity(150);
        flight.setOrigin("X");
        flight.setDestination("Y");
        flight.setNbr("FL000");
        flight.setDeparture(departureDate);

        given(reservationService.searchDatedFlights(anyString(), anyString(), any(), any()))
                .willReturn(Flux.just(flight));

        this.webTestClient.get()
                .uri("/search/from/to/2018-05-05")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(id.toHexString())
                .jsonPath("$.[0].airline").isEqualTo("Delta")
                .jsonPath("$.[0].capacity").isEqualTo(150)
                .jsonPath("$.[0].origin").isEqualTo("X")
                .jsonPath("$.[0].destination").isEqualTo("Y")
        ;
    }

}