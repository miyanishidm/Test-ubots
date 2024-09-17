package com.ubots.teste_ubots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan()
public class TesteUbotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteUbotsApplication.class, args);
	}

}
