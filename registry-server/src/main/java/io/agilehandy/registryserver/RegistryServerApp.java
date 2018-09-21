package io.springframework.registryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryServerApp {

	public static void main(String[] args) {
		SpringApplication.run(RegistryServerApp.class, args);
	}
}
