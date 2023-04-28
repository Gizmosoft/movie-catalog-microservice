package com.gizmosoft.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
public class MovieCatalogServiceApplication {

	// The below tells spring to create a Bean of type RestTemplate
	// Whenever anything of type RestTemplate is called using @Autowired in this Spring Application, this instance of RestTemplate will be called and given to it because by default Spring Beans are Singleton (which create only one instance)

	// Using @LoadBalanced annotation, we can avoid hardcoding of service URL endpoints. We are now asking discovery server to help us with the service that is required to be contacted. Discovery server knows services based on the names that we have defined them with in Eureka and not any specific URLs.
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		// HttpComponentsClientHttpRequestFactory helps us create timeout property and pass it on to the restTemplate client
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(3000);
		// passing the timeout property
		return new RestTemplate(clientHttpRequestFactory);
	}

	// Creating Bean of type WebClient for handling Asynchronous requests
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
		System.out.println("Created a Rest Controller!");
	}

}
