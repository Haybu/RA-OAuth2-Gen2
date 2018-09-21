package io.agilehandy.flights;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Flux;

import java.text.ParseException;
import java.util.List;

@SpringBootApplication
@Slf4j
public class FlightsServiceApp {

	@Value("${server.context-path:/}")
	private String contextPath;

    private final FlightsRepository flightsRepository;

    public FlightsServiceApp(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(FlightsServiceApp.class, args);
	}

	@Bean
    DataSeed dataSeed(@Value("${data.filepath}") String datafile) {
		return new DataSeed(datafile);
	}

	@Bean
	@Profile("!test")
	ApplicationRunner initializer(DataSeed dataSeed) throws ParseException {
		log.info("Initializing database...");
		List<Flight> flights = dataSeed.read();
		return args -> {
			//@formatter:off
            flightsRepository
					.deleteAll()
					.thenMany(
							Flux.fromStream(flights.stream())
									.flatMap(flight -> flightsRepository.save(flight))
									.thenMany(flightsRepository.findAll())
					)
					.subscribe(System.out::println)
			;
			//@formatter:on

		};
	}

	// set context path
	@Bean
	public WebFilter contextPathWebFilter() {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			if (request.getURI().getPath().startsWith(this.contextPath)) {
				return chain.filter(
						exchange.mutate()
								.request(request.mutate().contextPath(this.contextPath).build())
								.build());
			}
			return chain.filter(exchange);
		};
	}

	/**
	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Converter toString = new Converter<LocalDate, String>() {
            @Override
            public String convert(@NonNull LocalDate source) {
                return source.format(dateTimeFormatter);
            }
        };

		Converter toDate = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(@NonNull String source) {
                return LocalDate.parse(source, dateTimeFormatter);
            }
        };

		List<Converter> list = Arrays.asList(toString, toDate);
		return new MongoCustomConversions(list);
	}
    */

}
