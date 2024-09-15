package com.rays.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rays.gateway.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		System.out.println("in routes ====>>>>>>");
		return builder.routes()
				.route("auth-service",
						r -> r.path("/auth-service/**").filters(f -> f.filter(filter)).uri("lb://auth-service"))
				.route("service02", r -> r.path("/service02/**").filters(f -> f.filter(filter)).uri("lb://service02"))
				.build();
	}

}
