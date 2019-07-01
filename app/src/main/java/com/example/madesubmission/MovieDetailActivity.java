package com.example.madesubmission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private ImageView imgPhotoMovie;
    private TextView tvTitleMovie, tvDateMovie,
            tvDirectorMovie, tvScreenplayMovie, tvOverviewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgPhotoMovie = findViewById(R.id.img_photo_movie);
        tvTitleMovie = findViewById(R.id.tv_title_movie);
        tvDateMovie = findViewById(R.id.tv_date_movie);
        tvDirectorMovie = findViewById(R.id.tv_director_movie);
        tvScreenplayMovie = findViewById(R.id.tv_screenplay_movie);
        tvOverviewMovie = findViewById(R.id.tv_overview_movie);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        showDetailMovie(movie);
    }

    private void showDetailMovie(Movie movie) {
        Glide.with(this).load(movie.getPhoto()).into(imgPhotoMovie);
        tvTitleMovie.setText(movie.getTitle());
        tvDateMovie.setText(movie.getReleaseDate());
        tvDirectorMovie.setText(movie.getDirector());
        tvScreenplayMovie.setText(movie.getScreenPlay());
        tvOverviewMovie.setText(movie.getOverview());
    }
}
