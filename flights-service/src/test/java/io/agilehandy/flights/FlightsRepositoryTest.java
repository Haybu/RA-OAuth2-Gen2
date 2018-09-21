package io.agilehandy.flights;

import org.assertj.core.api.Assertions;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataMongoTest
@TestPropertySource(properties = {
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false",
        "spring.cloud.config.enabled=false",
        "data.filepath=/flights.dat"
})
public class FlightsRepositoryTest {

    @Autowired
    private FlightsRepository repository;

    Flight flight;
    LocalDate departureDate = LocalDate.now();
    final ObjectId id = new ObjectId();

    @Before
    public void setUp() throws Exception {
        flight = new Flight();
        flight.setId(id.toHexString());
        flight.setAirline("American");
        flight.setCapacity(200);
        flight.setOrigin("X");
        flight.setDestination("Y");
        flight.setNbr("FL000");
        flight.setDeparture(departureDate);

        this.repository.save(flight)
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
        StepVerifier.create(repository.findById((id)))
                .consumeNextWith(flight -> {
                    Assertions.assertThat(flight.getId()).isEqualTo(id.toHexString());
                    Assertions.assertThat(flight.getAirline()).isEqualTo("American");
                    Assertions.assertThat(flight.getCapacity()).isEqualTo(200);
                })
                .verifyComplete();
    }
}
