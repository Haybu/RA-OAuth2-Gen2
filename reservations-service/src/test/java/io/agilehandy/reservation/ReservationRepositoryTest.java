package io.agilehandy.reservation;

import io.agilehandy.reservation.entities.Reservation;
import org.assertj.core.api.Assertions;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.UUID;


@RunWith(SpringRunner.class)
@DataMongoTest
@TestPropertySource(properties = {
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false",
        "spring.cloud.config.enabled=false"
})
@ActiveProfiles("test")
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository repository;

    Reservation reservation;
    LocalDate departureDate = LocalDate.now();
    final ObjectId id = new ObjectId();
    final String confirimationNumber = UUID.randomUUID().toString();

    @Before
    public void setUp() throws Exception {
        reservation = new Reservation();
        reservation.setId(id.toHexString());
        reservation.setConfirmationNumber(confirimationNumber);
        reservation.setOrigin("X");
        reservation.setDestination("Y");
        reservation.setFlightNumber("FL000");
        reservation.setDeparture(departureDate);

        this.repository.save(reservation)
                .then()
                .block();
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll()
                .then()
                .block();
    }

    @Test
    public void findFlightById_returnsFlight() {
        StepVerifier.create(repository.findById((id.toHexString())))
                .consumeNextWith(r -> {
                    Assertions.assertThat(r.getId()).isEqualTo(id.toHexString());
                    Assertions.assertThat(r.getConfirmationNumber()).isEqualTo(confirimationNumber);
                    Assertions.assertThat(r.getDeparture()).isEqualTo(departureDate);
                    Assertions.assertThat(r.getOrigin()).isEqualTo("X");
                    Assertions.assertThat(r.getDestination()).isEqualTo("Y");
                    Assertions.assertThat(r.getFlightNumber()).isEqualTo("FL000");
                    Assertions.assertThat(r.getPassengers()).isNull();
                })
                .verifyComplete();
    }

}