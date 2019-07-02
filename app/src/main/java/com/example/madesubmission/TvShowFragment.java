package com.example.madesubmission;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madesubmission.adapter.TvShowAdapter;
import com.example.madesubmission.model.TvShow;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {
    private String[] dataTitle;
    private String[] dataDate;
    private String[] dataCreator;
    private String[] dataOverview;
    private TypedArray dataPhoto;
    private RecyclerView rvTvShow;
    private final ArrayList<TvShow> tvShows = new ArrayList<>();

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTvShow = view.findViewById(R.id.rv_tv_show);
        rvTvShow.setHasFixedSize(true);

        prepare();
        addItem();
    }

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.tv_show_title);
        dataDate = getResources().getStringArray(R.array.tv_show_release);
        dataCreator = getResources().getStringArray(R.array.tv_show_creator);
        dataOverview = getResources().getStringArray(R.array.tv_show_overview);
        dataPhoto = getResources().obtainTypedArray(R.array.tv_show_photo);
    }

    private void addItem() {
        for (int i = 0; i < dataTitle.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setPhoto(dataPhoto.getResourceId(i, -1));
            tvShow.setTitle(dataTitle[i]);
            tvShow.setReleaseDate(dataDate[i]);
            tvShow.setCreator(dataCreator[i]);
            tvShow.setOverview(dataOverview[i]);
            tvShows.add(tvShow);
        }

        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        TvShowAdapter adapter = new TvShowAdapter(getActivity());
        adapter.setListTvShow(tvShows);
        rvTvShow.setAdapter(adapter);

        ItemClickSupport.addTo(rvTvShow).setOnItemClicListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
                intent.putExtra(TvShowDetailActivity.EXTRA_TV, tvShows.get(position));
                startActivity(intent);
            }
        });
    }
}
