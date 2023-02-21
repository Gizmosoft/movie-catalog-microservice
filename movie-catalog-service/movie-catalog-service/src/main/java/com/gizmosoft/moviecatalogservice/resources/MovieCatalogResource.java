package com.gizmosoft.moviecatalogservice.resources;

import com.gizmosoft.moviecatalogservice.model.CatalogItem;
import com.gizmosoft.moviecatalogservice.model.Movie;
import com.gizmosoft.moviecatalogservice.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        RestTemplate restTemplate = new RestTemplate();

        // get ID of rated movies - hard coding using the Rating model
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)

        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Movie about sinking ships", rating.getRating());
        }).collect(Collectors.toList());

        // For each movie ID, call movie info service and get details

        // Put them all together

//        return Collections.singletonList(
//                new CatalogItem("Titanic","Movie about sinking ships", 5)
//        );
    }
}
