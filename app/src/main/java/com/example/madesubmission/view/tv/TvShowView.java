package com.example.madesubmission.view.tv;

import com.example.madesubmission.data.model.TvShow;

import java.util.List;

public interface TvShowView {
    void showLoading();
    void hideLoading();
    void showTvList(List<TvShow> tvShowList);
}
