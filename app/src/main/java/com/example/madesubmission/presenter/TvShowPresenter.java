package com.example.madesubmission.presenter;

import android.util.Log;

import com.example.madesubmission.data.api.ApiRepository;
import com.example.madesubmission.data.api.ApiService;
import com.example.madesubmission.data.model.response.TvShowResponse;
import com.example.madesubmission.view.movie.MovieView;
import com.example.madesubmission.view.tv.TvShowView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TvShowPresenter {
    private ApiService service;
    private TvShowView tvShowView;

    public TvShowPresenter(ApiService service, TvShowView tvShowView) {
        this.service = service;
        this.tvShowView = tvShowView;
    }

    public void getTvShow() {
        tvShowView.showLoading();
        service.create()
                .create(ApiRepository.class)
                .getTvShows()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TvShowResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TvShowResponse tvShowResponse) {
                        tvShowView.showTvList(tvShowResponse.getTvShowList());
                        tvShowView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError: ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete: ", "Complete");
                    }
                });

    }
}
