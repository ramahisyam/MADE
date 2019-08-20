package com.example.madesubmission.view.home.movie;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.madesubmission.R;
import com.example.madesubmission.data.model.Movies;
import com.example.madesubmission.data.model.response.MovieResponse;
import com.example.madesubmission.viewmodel.MovieViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private ArrayList<Movies> movieList = new ArrayList<>();
    private ProgressBar progressBar;
    MovieViewModel viewModel;
    MovieAdapter adapter;
    RecyclerView rvMovie;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovie();
        showLoading(true);
        viewModel.getListMovie().observe(this, getMovie);

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

        rvMovie.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        adapter = new MovieAdapter(getActivity());
        adapter.setMovieList(movieList);
        rvMovie.setAdapter(adapter);
//        onItemClick();
    }

    private Observer<MovieResponse> getMovie = new Observer<MovieResponse>() {
        @Override
        public void onChanged(@Nullable MovieResponse movieResponse) {
            adapter.setData(movieResponse);
            showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
