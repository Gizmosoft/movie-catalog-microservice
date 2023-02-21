package com.gizmosoft.moviecatalogservice.model;

public class Movie {
    private String movieId;
    private String name;

    // Empty constructor is required when we marshal and unmarshal resources into objects using Java
    public Movie(){

    }

    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
