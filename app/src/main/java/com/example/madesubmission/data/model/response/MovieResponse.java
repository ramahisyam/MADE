package com.example.madesubmission.data.model.response;

import com.example.madesubmission.data.model.Movies;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<Movies> moviesList = new ArrayList<>();

    public List<Movies> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }
}
