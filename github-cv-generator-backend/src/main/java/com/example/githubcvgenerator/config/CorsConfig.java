package com.example.githubcvgenerator.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ClientSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class CorsConfig{
	
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
	    return http
	        .cors().configurationSource(createCorsConfigSource()).and()
	        // your security rules
	        .build();
	}

	public CorsConfigurationSource createCorsConfigSource() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("http://localhost:3000");
	    config.addAllowedMethod("GET");

	    source.registerCorsConfiguration("/**", config);
	    return source;
	}
	
}
