package com.gizmosoft.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MovieCatalogServiceApplication {

	// The below tells spring to create a Bean of type RestTemplate
	// Whenever anything of type RestTemplate is called using @Autowired in this Spring Application, this instance of RestTemplate will be called and given to it because by default Spring Beans are Singleton (which create only one instance)
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
		System.out.println("Created a Rest Controller!");
	}

}
