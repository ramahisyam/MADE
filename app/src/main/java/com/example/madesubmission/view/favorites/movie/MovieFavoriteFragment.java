package com.example.madesubmission.view.favorites.movie;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.madesubmission.R;
import com.example.madesubmission.data.db.MovieHelper;
import com.example.madesubmission.data.model.Movies;
import com.example.madesubmission.view.LoadMoviesCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.example.madesubmission.view.home.movie.MovieDetailActivity.EXTRA_MOVIE;
import static com.example.madesubmission.view.home.movie.MovieDetailActivity.REQUEST_ADD;
import static com.example.madesubmission.view.home.movie.MovieDetailActivity.RESULT_ADD;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment implements LoadMoviesCallback {
    private static final String EXTRA_STATE = "EXTRA_STATE";
    private ArrayList<Movies> movieList = new ArrayList<>();
    MovieFavoriteAdapter adapter;
    RecyclerView rvMovie;
    MovieHelper movieHelper;
    ProgressBar progressBar;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress_bar_movie_favorite);
        rvMovie = view.findViewById(R.id.rv_movie_favorite);
        rvMovie.setLayoutManager(new StaggeredGridLayoutManager(2, 1));

        movieHelper = MovieHelper.getInstance(getContext());
        movieHelper.open();

        adapter = new MovieFavoriteAdapter(getContext());
        adapter.setListMovie(movieList);
        rvMovie.setAdapter(adapter);

        if (savedInstanceState == null) {
            new LoadMoviesAsync(movieHelper, this).execute();
        } else {
            ArrayList<Movies> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListMovie(list);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListMovie());
    }

    @Override
    public void preExecute() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public void postExecute(ArrayList<Movies> movies) {
        progressBar.setVisibility(View.INVISIBLE);
        adapter.setListMovie(movies);
    }

    private static class LoadMoviesAsync extends AsyncTask<Void, Void, ArrayList<Movies>> {
        private final WeakReference<MovieHelper> weakMovieHelper;
        private final WeakReference<LoadMoviesCallback> weakCallback;

        private LoadMoviesAsync(MovieHelper movieHelper, LoadMoviesCallback callback) {
            this.weakMovieHelper = new WeakReference<>(movieHelper);
            this.weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Movies> doInBackground(Void... voids) {
            return weakMovieHelper.get().getAllMovies();

        }

        @Override
        protected void onPostExecute(ArrayList<Movies> movies) {
            super.onPostExecute(movies);

            weakCallback.get().postExecute(movies);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == REQUEST_ADD) {
                if (resultCode == RESULT_ADD) {
                    Movies movies = data.getParcelableExtra(EXTRA_MOVIE);
                    adapter.addItem(movies);
                    rvMovie.smoothScrollToPosition(adapter.getItemCount() - 1);
                    Toast.makeText(getContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }
}
