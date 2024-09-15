# Spring Cloud Case Study

![](https://gitlab.nenosystems.in/cuickdevteam/spring-cloud-case-study/-/wikis/uploads/adb4ceb7b9a402fbfe210cb03a1427af/spring-cloud_v1.png)
---

## discovery-server

Discovery Server (also known as Service Registry) is a crucial component that helps manage and facilitate service discovery within the system. It keeps track of all the available services in the network and provides an up-to-date directory of their locations and network addresses. This enables services to locate and communicate with each other dynamically without hardcoding their network locations.

In the Spring ecosystem, Spring Cloud Netflix Eureka is a popular choice for implementing a Discovery Server. It allows services to register themselves with the Discovery Server upon startup and periodically update their status. Other services can then query the Discovery Server to discover and locate the available services as needed.

**dependency:**

    <dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
	</dependency>

**main class:**

    @SpringBootApplication
    @EnableEurekaServer
    public class DiscoveryServerApplication {
	    public static void main(String[] args) {
		    SpringApplication.run(DiscoveryServerApplication.class, args);
	    }
    }

**application.yml:**

    server:
        port: 8761
    spring:
        application:
            name: discovery-server
    eureka:
        client:
            registerWithEureka: false
            fetchRegistry: false

**Run the Eureka Server:** Run your Spring Boot application, and your Eureka Server should start at the specified port (default is [http://localhost:8761](http://localhost:8761)).

**Verify Eureka Server Dashboard:** Open your web browser and go to the Eureka Server dashboard ([http://localhost:8761](http://localhost:8761)).

![](https://gitlab.nenosystems.in/cuickdevteam/spring-cloud-case-study/-/wikis/uploads/94dc9cf7b9ede210bb50cea3851400db/Screenshot__54_.png)

You should see the Eureka dashboard displaying information about registered instances.!

## config-server

Config Server is a central component responsible for managing the configuration properties of all the services in the system. It provides a centralized location for storing configuration properties in a version-controlled repository, such as Git, SVN, or local file system.

**dependency:**

    <dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-config-server</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>

**main class:**

    @SpringBootApplication
    @EnableConfigServer
    public class ConfigServerApplication {
	    public static void main(String[] args) {
		    SpringApplication.run(ConfigServerApplication.class, args);
	    }
    }

**application.yml:**

    server:
        port: 8888
    spring:
        application:
            name: config-server
    cloud:
        config:
            server:
                git:
                    uri: https://github.com/SawanPanwar/Spring-Cloud-Config.git
                    clone-on-start: true

## api-gateway

API Gateway is a common architectural pattern used in microservices-based applications to manage and route incoming requests from clients to the appropriate microservices. It serves as a single entry point for clients to access various services within the system. The API Gateway handles tasks such as authentication, authorization, load balancing, routing, and protocol translation.

**dependency:**

    <dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-gateway</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-config</artifactId>
	</dependency>

**main class:**

    @SpringBootApplication
    @EnableDiscoveryClient
    public class ApiGatewayApplication {
	    public static void main(String[] args) {
		    SpringApplication.run(ApiGatewayApplication.class, args);
	    }
    }

**make a GatewayConfig class:**    

    @Configuration
    public class GatewayConfig {

	    @Bean
	    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		    return builder.routes().route("service01", r -> r.path("/service01/**").uri("lb://service01"))
				.route("service02", r -> r.path("/service02/**").uri("lb://service02"))
				.route("ORS10", r -> r.path("/ORS10/**").uri("lb://ORS10")).build();
	    }
    }

**application.yml:**

    server:
        host: localhost
        port: 8080
    spring:
        main:
            web-application-type: reactive
        application:
            name: api-gateway
    config:
        import: configserver:http://localhost:8888


## microservices

In Spring Cloud, microservices are typically developed using Spring Boot, which simplifies the setup and development of stand-alone, production-grade Spring-based applications. Spring Boot provides various features, such as auto-configuration, embedded servers, and production-ready metrics, which are essential for building microservices.

**dependency:**

	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-config</artifactId>
	</dependency>

**main class:**

    @SpringBootApplication
    @EnableDiscoveryClient
    public class Service01Application {
	    public static void main(String[] args) {
		    SpringApplication.run(Service01Application.class, args);
	    }
    }

**application.yml:**

    server:
        port: 8081
        servlet:
            context-path: /service01
    spring:
        application:
            name: service01
    config:
        import: configserver:http://localhost:8888

