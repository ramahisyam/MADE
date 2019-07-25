package com.example.madesubmission.view.tv;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.data.model.Movies;
import com.example.madesubmission.data.model.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV = "extra_tv";
    private ImageView imgPhoto;
    private TextView tvTitle, tvRelease, tvVoteAverage, tvOverview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        imgPhoto = findViewById(R.id.img_photo_tv);
        tvTitle = findViewById(R.id.txt_title_tv);
        tvRelease = findViewById(R.id.txt_release_tv);
        tvVoteAverage = findViewById(R.id.txt_rating_tv);
        tvOverview = findViewById(R.id.txt_overview_tv);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV);
        showData(tvShow);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void showData(TvShow tvShow) {
        Glide.with(this).load("https://image.tmdb.org/t/p/original"+tvShow.getPosterPath()).into(imgPhoto);
        tvTitle.setText(tvShow.getName());
        tvRelease.setText(tvShow.getFirstAirDate());
        tvVoteAverage.setText(String.valueOf(tvShow.getVoteAverage()));
        tvOverview.setText(tvShow.getOverview());
    }
}
