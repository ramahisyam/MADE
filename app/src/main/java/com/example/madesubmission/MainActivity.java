package com.example.madesubmission;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.madesubmission.adapter.MovieAdapter;
import com.example.madesubmission.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String movieTitle[];
    private String movieRelease[];
    private String movieDescription[];
    private TypedArray moviePhoto;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);

        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies.get(position));
                startActivity(intent);
            }
        });
    }

    private void addItem() {
        movies = new ArrayList<>();

        for (int i = 0; i < movieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(moviePhoto.getResourceId(i, -1));
            movie.setTitle(movieTitle[i]);
            movie.setRelease(movieRelease[i]);
            movie.setDescription(movieDescription[i]);
            movies.add(movie);
        }
        adapter.setMovies(movies);
    }

    private void prepare() {
        movieTitle = getResources().getStringArray(R.array.movie_name);
        movieRelease = getResources().getStringArray(R.array.movie_release);
        movieDescription = getResources().getStringArray(R.array.movie_description);
        moviePhoto = getResources().obtainTypedArray(R.array.movie_photo);
    }
}
