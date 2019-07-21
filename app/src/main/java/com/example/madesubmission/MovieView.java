package com.example.madesubmission;

import com.example.madesubmission.data.model.Movies;

import java.util.List;

public interface MovieView {
    void showLoading();
    void hideLoading();
    void showMovieList(List<Movies> moviesList);
}
