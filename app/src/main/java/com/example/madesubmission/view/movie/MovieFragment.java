package com.example.madesubmission.view.movie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.madesubmission.R;
import com.example.madesubmission.data.api.ApiService;
import com.example.madesubmission.data.model.Movies;
import com.example.madesubmission.presenter.MoviePresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieView {
    private ArrayList<Movies> movieList = new ArrayList<>();
    private ProgressBar progressBar;
    MoviePresenter presenter;
    MovieAdapter adapter;
    RecyclerView rvMovie;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new MoviePresenter(new ApiService(), this);
        presenter.getMovie();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar_movie);

        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(getActivity());
        adapter.setMovieList(movieList);
        rvMovie.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMovieList(List<Movies> moviesList) {
        movieList.clear();
        movieList.addAll(moviesList);
        adapter.notifyDataSetChanged();
    }
}
