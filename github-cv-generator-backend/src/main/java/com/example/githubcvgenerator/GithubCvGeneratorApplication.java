package com.example.githubcvgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@SpringBootApplication
public class GithubCvGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubCvGeneratorApplication.class, args);
	}
	
	

}
