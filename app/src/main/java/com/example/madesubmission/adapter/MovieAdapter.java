package com.example.madesubmission.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(convertView);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie, convertView);
        return convertView;
    }

    private class ViewHolder {
        private TextView txtTitle, txtRelease;
        private ImageView imgPhoto;

        ViewHolder (View view) {
            txtTitle = view.findViewById(R.id.txt_title);
            txtRelease = view.findViewById(R.id.txt_release_date);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Movie movie, View view) {
            txtTitle.setText(movie.getTitle());
            txtRelease.setText(movie.getRelease());
            Glide.with(view).load(movie.getPhoto()).into(imgPhoto);
        }
    }
}
