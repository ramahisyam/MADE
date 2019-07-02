package com.example.madesubmission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private ImageView imgPhoto;
    private TextView tvTitle, tvDate,
            tvDirector, tvScreenplay, tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgPhoto = findViewById(R.id.img_photo_movie);
        tvTitle = findViewById(R.id.tv_title_movie);
        tvDate = findViewById(R.id.tv_date_movie);
        tvDirector = findViewById(R.id.tv_director_movie);
        tvScreenplay = findViewById(R.id.tv_screenplay_movie);
        tvOverview = findViewById(R.id.tv_overview_movie);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        showData(movie);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(movie.getTitle());
        }
    }

    private void showData(Movie movie) {
        Glide.with(this).load(movie.getPhoto()).into(imgPhoto);
        tvTitle.setText(movie.getTitle());
        tvDate.setText(movie.getReleaseDate());
        tvDirector.setText(movie.getDirector());
        tvScreenplay.setText(movie.getScreenPlay());
        tvOverview.setText(movie.getOverview());
    }
}
