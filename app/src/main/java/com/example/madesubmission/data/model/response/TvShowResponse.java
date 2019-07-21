package com.example.madesubmission.data.model.response;

import com.example.madesubmission.data.model.TvShow;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShowResponse {
    @SerializedName("result")
    private List<TvShow> tvShowList = new ArrayList<>();

    public List<TvShow> getTvShowList() {
        return tvShowList;
    }

    public void setTvShowList(List<TvShow> tvShowList) {
        this.tvShowList = tvShowList;
    }
}
