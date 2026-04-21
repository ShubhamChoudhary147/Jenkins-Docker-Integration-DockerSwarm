package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}


// UI --> API Gateway --> Routes
// eg. spring.cloud.gateway.routes[0].id=customer-service
// spring.cloud.gateway.routes[0].uri=lb://customer-service
// spring.cloud.gateway.routes[0].predicates[0]=Path=/customer/**