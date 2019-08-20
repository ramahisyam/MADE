package com.example.madesubmission.view.home.tv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.data.model.Movies;
import com.example.madesubmission.data.model.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV = "extra_tv";
    private ImageView imgBackDrop, imgPoster;
    private TextView tvTitle, tvVoteAverage, tvReleaseDate, tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        imgBackDrop = findViewById(R.id.tv_show_back_drop);
        imgPoster = findViewById(R.id.tv_show_poster);
        tvTitle = findViewById(R.id.tv_show_title);
        tvVoteAverage = findViewById(R.id.tv_show_vote_average);
        tvReleaseDate = findViewById(R.id.tv_show_release_date);
        tvOverview = findViewById(R.id.tv_show_overview);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV);
        showData(tvShow);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(tvShow.getName());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showData(TvShow tvShow) {
        Glide.with(this).load("https://image.tmdb.org/t/p/original"+tvShow.getBackdropPath()).into(imgBackDrop);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+tvShow.getPosterPath()).into(imgPoster);
        tvTitle.setText(tvShow.getName());
        tvVoteAverage.setText(String.valueOf(tvShow.getVoteAverage()));
        tvReleaseDate.setText(tvShow.getFirstAirDate());
        tvOverview.setText(tvShow.getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
