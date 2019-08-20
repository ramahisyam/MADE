package com.example.madesubmission.view.favorites;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madesubmission.R;
import com.example.madesubmission.view.PagerAdapter;
import com.example.madesubmission.view.favorites.movie.MovieFavoriteFragment;
import com.example.madesubmission.view.favorites.tv.TvShowFavoriteFragment;
import com.example.madesubmission.view.home.movie.MovieFragment;
import com.example.madesubmission.view.home.tv.TvShowFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.view_pager_favorite);
        setFavoriteViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tabs_favorite);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    private void setFavoriteViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addingFragment(new MovieFavoriteFragment(), "Movie");
        adapter.addingFragment(new TvShowFavoriteFragment(), "Tv Show");
        viewPager.setAdapter(adapter);
    }

}
