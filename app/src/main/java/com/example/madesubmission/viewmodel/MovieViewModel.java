package com.example.madesubmission.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.madesubmission.data.api.ApiRepository;
import com.example.madesubmission.data.api.ApiService;
import com.example.madesubmission.data.model.response.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<MovieResponse> listMovie;

    public MovieViewModel() {
        this.listMovie = new MutableLiveData<>();
    }

    public void getMovie() {
        ApiService.create()
                .create(ApiRepository.class)
                .getMovies()
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        listMovie.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.d("onFailure: ", t.getMessage());
                    }
                });
    }

    public LiveData<MovieResponse> getListMovie() {
        return listMovie;
    }
}
