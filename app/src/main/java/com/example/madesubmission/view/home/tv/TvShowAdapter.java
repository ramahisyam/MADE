package com.example.madesubmission.view.home.tv;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.madesubmission.R;
import com.example.madesubmission.data.model.TvShow;
import com.example.madesubmission.data.model.response.TvShowResponse;
import com.example.madesubmission.utils.CustomOnItemClickListener;
import com.example.madesubmission.view.viewholder.CatalogueViewHolder;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<CatalogueViewHolder> {
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

    public void setData(TvShowResponse items) {
        tvShowList.clear();
        tvShowList.addAll(items.getTvShowList());
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
        viewHolder.tvTitle.setText(tvShowList.get(i).getName());
        viewHolder.tvReleaseDate.setText(tvShowList.get(i).getFirstAirDate());
        viewHolder.tvVoteAverage.setText(String.valueOf(tvShowList.get(i).getVoteAverage()));
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + getTvShowList()
                        .get(i)
                        .getPosterPath())
                .into(viewHolder.imgPoster);
        viewHolder.cardView.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, TvShowDetailActivity.class);
                intent.putExtra(TvShowDetailActivity.EXTRA_TV, tvShowList.get(i));
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getTvShowList().size();
    }
}
