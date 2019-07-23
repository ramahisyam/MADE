package com.example.madesubmission.view.tv;

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
import com.example.madesubmission.data.model.TvShow;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private final Context context;
    private ArrayList<TvShow> tvShowList;

    TvShowAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<TvShow> getTvShowList() {
        return tvShowList;
    }

    void setTvShowList(ArrayList<TvShow> tvShowList) {
        this.tvShowList = tvShowList;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TvShowViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder tvShowViewHolder, int i) {
        tvShowViewHolder.tvTitle.setText(tvShowList.get(i).getName());
        tvShowViewHolder.tvOverview.setText(tvShowList.get(i).getOverview());
        tvShowViewHolder.tvVoteAverage.setText(String.valueOf(tvShowList.get(i).getVoteAverage()));
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w92" + getTvShowList()
                        .get(i)
                        .getPosterPath())
                .into(tvShowViewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getTvShowList().size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOverview, tvVoteAverage;
        ImageView imgPoster;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvVoteAverage = itemView.findViewById(R.id.tv_vote_average);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
