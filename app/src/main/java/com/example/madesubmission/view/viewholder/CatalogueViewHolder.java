package com.example.madesubmission.view.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madesubmission.R;

public class CatalogueViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle, tvReleaseDate, tvVoteAverage;
    public ImageView imgPoster;
    public CardView cardView;

    public CatalogueViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
        tvVoteAverage = itemView.findViewById(R.id.tv_vote_average);
        imgPoster = itemView.findViewById(R.id.img_poster);
        cardView = itemView.findViewById(R.id.card_view);
    }
}
