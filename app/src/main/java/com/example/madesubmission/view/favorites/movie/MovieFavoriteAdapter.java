package com.example.madesubmission.view.favorites.movie;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.data.model.Movies;
import com.example.madesubmission.view.viewholder.CatalogueViewHolder;

import java.util.ArrayList;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<CatalogueViewHolder>{
    private ArrayList<Movies> listMovie = new ArrayList<>();
    private Context context;

    public MovieFavoriteAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Movies> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movies> listMovie) {
        if (listMovie.size() > 0) {
            this.listMovie.clear();
        }
        this.listMovie.addAll(listMovie);
        notifyDataSetChanged();
    }

    public void addItem(Movies movies) {
        this.listMovie.add(movies);
        notifyItemInserted(listMovie.size() - 1);
    }

    public void removeItem(int position) {
        this.listMovie.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listMovie.size());
    }

    @NonNull
    @Override
    public CatalogueViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CatalogueViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogueViewHolder catalogueViewHolder, int i) {
        catalogueViewHolder.tvTitle.setText(listMovie.get(i).getTitle());
        catalogueViewHolder.tvReleaseDate.setText(listMovie.get(i).getReleaseDate());
        catalogueViewHolder.tvVoteAverage.setText(String.valueOf(listMovie.get(i).getVoteAverage()));
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + getListMovie()
                .get(i)
                .getPosterPath()).into(catalogueViewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
