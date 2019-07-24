package com.example.madesubmission.view.tv;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.madesubmission.R;
import com.example.madesubmission.data.api.ApiService;
import com.example.madesubmission.data.model.TvShow;
import com.example.madesubmission.data.model.response.TvShowResponse;
import com.example.madesubmission.presenter.TvShowPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment{
    private ArrayList<TvShow> tvShowsList = new ArrayList<>();
    private ProgressBar progressBar;
    TvShowPresenter presenter;
    TvShowAdapter adapter;
    RecyclerView rvMovie;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = ViewModelProviders.of(this).get(TvShowPresenter.class);
        presenter.getTvShow();
        showLoading(true);
        presenter.getListTv().observe(this, getTv);

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
        rvMovie = view.findViewById(R.id.rv_tv);
        progressBar = view.findViewById(R.id.progress_bar_tv);

        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TvShowAdapter(getActivity());
        adapter.setTvShowList(tvShowsList);
        rvMovie.setAdapter(adapter);
    }

    private Observer<TvShowResponse> getTv = new Observer<TvShowResponse>() {
        @Override
        public void onChanged(@Nullable TvShowResponse tvShowResponse) {
            adapter.setData(tvShowResponse);
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
