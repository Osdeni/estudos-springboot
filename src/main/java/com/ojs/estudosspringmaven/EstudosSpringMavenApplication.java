package com.ojs.estudosspringmaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class EstudosSpringMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstudosSpringMavenApplication.class, args);
	}

}
