package com.gizmosoft.moviecatalogservice.services;

import com.gizmosoft.moviecatalogservice.model.CatalogItem;
import com.gizmosoft.moviecatalogservice.model.Movie;
import com.gizmosoft.moviecatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

        // Using WebClient
//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class) // Mono basically means to wait until an operation is completed.
//                    .block(); // block basically tells to block the method until the operation defined for Mono is completed. So we need to wait until we get all data from this method.
        // Put them all together
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found!", "", rating.getRating());
    }
}
