package com.gizmosoft.moviecatalogservice.resources;

import com.gizmosoft.moviecatalogservice.model.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        // get all rated movie ID

        // For each movie ID, call movie info services and get details

        // put them all together

        return Collections.singletonList(
                new CatalogItem("Titanic","Movie about sinking ships", 5)
        );
    }
}
