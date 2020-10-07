package com.robert.lostpets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

/**
 * Punto de entrada para la aplicación LostPets.
 *
 * @author Robert Ene
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan("com.robert.lostpets")
@EntityScan(basePackages = { "com.robert.lostpets.entity" })
@EnableJpaRepositories(basePackages = { "com.robert.lostpets.persistence" })
@EnableEncryptableProperties
public class LostPetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LostPetsApplication.class, args);
	}
}
