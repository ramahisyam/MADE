package com.example.madesubmission.view;

import com.example.madesubmission.data.model.Movies;

import java.util.ArrayList;

public interface LoadMoviesCallback {
    void preExecute();
    void postExecute(ArrayList<Movies> movies);
}
