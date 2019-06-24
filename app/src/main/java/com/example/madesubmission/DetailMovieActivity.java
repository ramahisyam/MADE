package com.example.madesubmission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private ImageView imgPhoto;
    private TextView txtTitle, txtReleaseDate, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        imgPhoto = findViewById(R.id.img_detail_photo);
        txtTitle = findViewById(R.id.txt_detail_title);
        txtReleaseDate = findViewById(R.id.txt_detail_release_date);
        txtDescription = findViewById(R.id.txt_description);

        Movie movies = getIntent().getParcelableExtra(EXTRA_MOVIE);
        showMovieDetail(movies);
    }

    private void showMovieDetail(Movie movies) {
        Glide.with(this).load(movies.getPhoto()).into(imgPhoto);
        txtTitle.setText(movies.getTitle());
        txtReleaseDate.setText(movies.getRelease());
        txtDescription.setText(movies.getDescription());
    }
}
