package com.example.madesubmission.view.home.movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.data.model.Movies;
import com.example.madesubmission.data.model.response.MovieResponse;
import com.example.madesubmission.utils.CustomOnItemClickListener;
import com.example.madesubmission.view.viewholder.CatalogueViewHolder;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<CatalogueViewHolder>{
    private final Context context;
    private ArrayList<Movies> movieList = new ArrayList<>();

    MovieAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Movies> getMovieList() {
        return movieList;
    }

    void setMovieList(ArrayList<Movies> movieList) {
        this.movieList = movieList;
    }

    public void setData(MovieResponse items) {
        movieList.clear();
        movieList.addAll(items.getMoviesList());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatalogueViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CatalogueViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogueViewHolder viewHolder, final int i) {
        viewHolder.tvTitle.setText(movieList.get(i).getTitle());
        viewHolder.tvReleaseDate.setText(movieList.get(i).getReleaseDate());
        viewHolder.tvVoteAverage.setText(String.valueOf(movieList.get(i).getVoteAverage()));
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + getMovieList()
                        .get(i)
                        .getPosterPath())
                .into(viewHolder.imgPoster);
        viewHolder.cardView.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movieList.get(i));
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getMovieList().size();
    }
}
