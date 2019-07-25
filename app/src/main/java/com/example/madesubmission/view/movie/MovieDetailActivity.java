package com.example.madesubmission.view.movie;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.data.model.Movies;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private ImageView imgPhoto;
    private TextView tvTitle, tvRelease, tvVoteAverage, tvOverview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        imgPhoto = findViewById(R.id.img_photo_movie);
        tvTitle = findViewById(R.id.tv_title_movie);
        tvRelease = findViewById(R.id.tv_release_movie);
        tvVoteAverage = findViewById(R.id.tv_rating);
        tvOverview = findViewById(R.id.tv_overview_movie);

        Movies movies = getIntent().getParcelableExtra(EXTRA_MOVIE);
        showData(movies);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void showData(Movies movies) {
        Glide.with(this).load("https://image.tmdb.org/t/p/original"+movies.getPosterPath()).into(imgPhoto);
        tvTitle.setText(movies.getTitle());
        tvRelease.setText(movies.getReleaseDate());
        tvVoteAverage.setText(String.valueOf(movies.getVoteAverage()));
        tvOverview.setText(movies.getOverview());
    }
}
