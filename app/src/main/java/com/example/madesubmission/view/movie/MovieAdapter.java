package com.example.madesubmission.view.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.data.model.Movies;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Context context;
    private ArrayList<Movies> movieList;

    MovieAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Movies> getMovieList() {
        return movieList;
    }

    void setMovieList(ArrayList<Movies> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.tvTitle.setText(movieList.get(i).getTitle());
        movieViewHolder.tvOverview.setText(movieList.get(i).getOverview());
        movieViewHolder.tvVoteAverage.setText(String.valueOf(movieList.get(i).getVoteAverage()));
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w92" + getMovieList()
                        .get(i)
                        .getPosterPath())
                .into(movieViewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getMovieList().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOverview, tvVoteAverage;
        ImageView imgPoster;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvVoteAverage = itemView.findViewById(R.id.tv_vote_average);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
