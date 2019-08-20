package com.example.madesubmission.view.home;


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
import com.example.madesubmission.view.home.movie.MovieFragment;
import com.example.madesubmission.view.PagerAdapter;
import com.example.madesubmission.view.home.tv.TvShowFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.view_pager_home);
        setHomeViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tabs_home);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void setHomeViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addingFragment(new MovieFragment(), "Movie");
        adapter.addingFragment(new TvShowFragment(), "Tv Show");
        viewPager.setAdapter(adapter);
    }

}
