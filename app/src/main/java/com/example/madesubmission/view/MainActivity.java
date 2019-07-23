package com.example.madesubmission.view;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.madesubmission.R;
import com.example.madesubmission.view.movie.MovieFragment;
import com.example.madesubmission.view.tv.TvShowFragment;

public class MainActivity extends AppCompatActivity {

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment;
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_movie:
                            fragment = new MovieFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                                    .commit();

                            return true;
                        case R.id.navigation_tv_show:
                            fragment = new TvShowFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
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

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_movie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_language) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
