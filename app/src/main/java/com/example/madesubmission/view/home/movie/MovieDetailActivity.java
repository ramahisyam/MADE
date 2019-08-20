package com.example.madesubmission.view.home.movie;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.data.db.MovieHelper;
import com.example.madesubmission.data.model.Movies;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_POSITION = "extra_position";

    private ImageView imgBackDrop, imgPoster;
    private TextView tvTitle, tvVoteAverage, tvReleaseDate, tvOverview;

    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;
    public static final int RESULT_DELETE = 301;

    private int position;
    private boolean isFavorite = false;
    private Movies movies;
    private MovieHelper movieHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgBackDrop = findViewById(R.id.movie_back_drop);
        imgPoster = findViewById(R.id.movie_poster);
        tvTitle = findViewById(R.id.movie_title);
        tvVoteAverage = findViewById(R.id.movie_vote_average);
        tvReleaseDate = findViewById(R.id.movie_release_date);
        tvOverview = findViewById(R.id.movie_overview);

        movieHelper = MovieHelper.getInstance(this);

        movies = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (movies != null) {
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isFavorite = true;
        } else {
            movies = new Movies();
        }

        showData(movies);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(movies.getTitle());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showData(Movies movies) {
        Glide.with(this).load("https://image.tmdb.org/t/p/original"+movies.getBackdropPath()).into(imgBackDrop);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movies.getPosterPath()).into(imgPoster);
        tvTitle.setText(movies.getTitle());
        tvVoteAverage.setText(String.valueOf(movies.getVoteAverage()));
        tvReleaseDate.setText(movies.getReleaseDate());
        tvOverview.setText(movies.getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
            case R.id.favorite:
                if (isFavorite) {
                    saveFavorite();
                    isFavorite = true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveFavorite() {
        movieHelper.open();

        Intent intent = new Intent();
        intent.putExtra(EXTRA_MOVIE, movies);
        intent.putExtra(EXTRA_POSITION, position);
        long result = movieHelper.insertMovie(movies);
        Log.d("saveFavorite: ", String.valueOf(result));
        if (result > 0) {
            movies.setId((int) result);
            setResult(RESULT_ADD, intent);
            Toast.makeText(this, movies.getTitle(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }
}
