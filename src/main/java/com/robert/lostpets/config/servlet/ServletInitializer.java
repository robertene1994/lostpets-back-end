package com.robert.lostpets.config.servlet;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.robert.lostpets.LostPetsApplication;

/**
 * Clase que inicializa el servlet de la aplicación.
 * 
 * @author Robert Ene
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LostPetsApplication.class);
	}
}
