package com.example.madesubmission.presenter;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.madesubmission.data.api.ApiRepository;
import com.example.madesubmission.data.api.ApiService;
import com.example.madesubmission.data.model.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowPresenter extends ViewModel {
    private MutableLiveData<TvShowResponse> listTv;

    public TvShowPresenter() {
        listTv = new MutableLiveData<>();
    }

    public void getTvShow() {
        ApiService.create()
                .create(ApiRepository.class)
                .getTvShows()
                .enqueue(new Callback<TvShowResponse>() {
                    @Override
                    public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                        listTv.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<TvShowResponse> call, Throwable t) {
                        Log.d("onFailure: ", t.getMessage());
                    }
                });

    }

    public LiveData<TvShowResponse> getListTv() {
        return listTv;
    }
}
