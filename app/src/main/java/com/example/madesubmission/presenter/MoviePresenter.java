package com.example.madesubmission.presenter;

import android.util.Log;

import com.example.madesubmission.MovieView;
import com.example.madesubmission.data.api.ApiRepository;
import com.example.madesubmission.data.api.ApiService;
import com.example.madesubmission.data.model.response.MovieResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter {
    private ApiService service;
    private MovieView movieView;

    public MoviePresenter(ApiService service, MovieView movieView) {
        this.service = service;
        this.movieView = movieView;
    }

    public void getMovie() {
        movieView.showLoading();
        service.create()
                .create(ApiRepository.class)
                .getMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        movieView.showMovieList(movieResponse.getMoviesList());
                        movieView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("OnError : ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete: ", "Complete");
                    }
                });

    }
}
