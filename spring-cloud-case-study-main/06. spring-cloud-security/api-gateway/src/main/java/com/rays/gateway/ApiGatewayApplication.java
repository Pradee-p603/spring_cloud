package com.rays.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiGatewayApplication.class, args);

	}

	/*
	 * @Bean public CorsWebFilter corsWebFilter() { CorsConfiguration corsConfig =
	 * new CorsConfiguration(); corsConfig.setAllowCredentials(true);
	 * 
	 * corsConfig.addAllowedOrigin("*");
	 * 
	 * corsConfig.addAllowedMethod("*");
	 * 
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * corsConfig);
	 * 
	 * return new CorsWebFilter(source); }
	 */

}
