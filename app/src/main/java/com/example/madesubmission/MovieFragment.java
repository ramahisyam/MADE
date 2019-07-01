package com.example.madesubmission;


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
    private TypedArray dataPhoto;
    private RecyclerView rvMovie;
    private ArrayList<Movie> movies = new ArrayList<>();

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
        dataPhoto = getResources().obtainTypedArray(R.array.movie_photo);
    }

    private void addItem(){
        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setReleaseDate(dataDate[i]);
            movies.add(movie);
        }

        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter adapter = new MovieAdapter(getActivity());
        adapter.setListMovie(movies);
        rvMovie.setAdapter(adapter);
    }

}
