package com.cerner.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BootHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootHibernateApplication.class, args);
	}
}
