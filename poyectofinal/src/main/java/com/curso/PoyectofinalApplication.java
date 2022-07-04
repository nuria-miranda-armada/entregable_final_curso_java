package com.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PoyectofinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoyectofinalApplication.class, args);
	}

}
