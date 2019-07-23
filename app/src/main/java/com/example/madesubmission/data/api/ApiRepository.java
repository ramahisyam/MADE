package com.example.madesubmission.data.api;

import com.example.madesubmission.BuildConfig;
import com.example.madesubmission.data.model.response.MovieResponse;
import com.example.madesubmission.data.model.response.TvShowResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiRepository {
    @GET("/3/discover/movie?api_key="+ BuildConfig.TMDB_API_KEY +"&language=en-US")
    Observable<MovieResponse> getMovies();

    @GET("/3/discover/tv?api_key="+ BuildConfig.TMDB_API_KEY +"&language=en-US")
    Observable<TvShowResponse> getTvShows();
}
