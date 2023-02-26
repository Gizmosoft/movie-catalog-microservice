package com.gizmosoft.moviecatalogservice.resources;

import com.gizmosoft.moviecatalogservice.model.CatalogItem;
import com.gizmosoft.moviecatalogservice.model.Movie;
import com.gizmosoft.moviecatalogservice.model.Rating;
import com.gizmosoft.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    // We are calling the bean which we created
    @Autowired
    private RestTemplate restTemplate;

    // Calling WebClient Bean instance
    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        // get ID of rated movies - hard coding using the Rating model
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3)
//        );
        // Using RestTemplate
        // get ID of rated movies - using API call to the Ratings service ; we are hard coding API endpoint URLs here which will be avoided when Eureka Discovery server is used
        // UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        // Using spring.application.name used in Eureka config for client side service discovery
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            // For each movie ID, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            // Using WebClient
//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class) // Mono basically means to wait until an operation is completed.
//                    .block(); // block basically tells to block the method until the operation defined for Mono is completed. So we need to wait until we get all data from this method.
            // Put them all together
            return new CatalogItem(movie.getName(), "Movie about sinking ships", rating.getRating());
        }).collect(Collectors.toList());

//        return Collections.singletonList(
//                new CatalogItem("Titanic","Movie about sinking ships", 5)
//        );
    }
}
