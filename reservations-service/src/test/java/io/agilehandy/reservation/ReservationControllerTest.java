package io.agilehandy.reservation;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
@WebFluxTest
@TestPropertySource(properties = {
        "eureka.client.register-with-eureka=false"
        ,"eureka.client.fetch-registry=false"
        ,"spring.cloud.config.enabled=false"
        //,"server.context-path=/reservations"
})
@ActiveProfiles("test")
public class ReservationControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    ReservationService reservationService;

    @Ignore
    @Test
    @WithMockUser(authorities = "SCOPE_reserve")
    public void reservartions_shouldBook() throws Exception {
        BDDMockito.given(reservationService.book(any()))
                .willReturn(Mono.just("1111"));

        this.webTestClient.post()
                .uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.confirmation").isEqualTo("1111")
        ;
    }

}