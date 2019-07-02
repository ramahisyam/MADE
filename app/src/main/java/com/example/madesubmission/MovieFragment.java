package com.example.madesubmission;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madesubmission.adapter.MovieAdapter;
import com.example.madesubmission.model.Movie;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private String[] dataTitle;
    private String[] dataDate;
    private String[] dataDirector;
    private String[] dataScreenplay;
    private String[] dataOverview;
    private TypedArray dataPhoto;
    private RecyclerView rvMovie;
    private final ArrayList<Movie> movies = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        prepare();
        addItem();
    }

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.movie_title);
        dataDate = getResources().getStringArray(R.array.movie_release);
        dataDirector = getResources().getStringArray(R.array.director);
        dataScreenplay = getResources().getStringArray(R.array.screen_play);
        dataOverview = getResources().getStringArray(R.array.movie_overview);
        dataPhoto = getResources().obtainTypedArray(R.array.movie_photo);
    }

    private void addItem(){
        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setReleaseDate(dataDate[i]);
            movie.setDirector(dataDirector[i]);
            movie.setScreenPlay(dataScreenplay[i]);
            movie.setOverview(dataOverview[i]);
            movies.add(movie);
        }

        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter adapter = new MovieAdapter(getActivity());
        adapter.setListMovie(movies);
        rvMovie.setAdapter(adapter);

        ItemClickSupport.addTo(rvMovie).setOnItemClicListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movies.get(position));
                startActivity(intent);
            }
        });
    }

}
