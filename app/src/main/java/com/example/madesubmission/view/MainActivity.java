package com.example.madesubmission.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.madesubmission.R;
import com.example.madesubmission.view.favorites.FavoriteFragment;
import com.example.madesubmission.view.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment;
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.main_container, fragment, fragment.getClass().getSimpleName())
                                    .commit();

                            return true;
                        case R.id.navigation_favorite:
                            fragment = new FavoriteFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.main_container, fragment, fragment.getClass().getSimpleName())
                                    .commit();

                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }
}
