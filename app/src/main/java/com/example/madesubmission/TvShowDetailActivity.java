package com.example.madesubmission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madesubmission.model.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV = "extra_tv";
    private ImageView imgPhoto;
    private TextView tvTitle, tvDate, tvCreator, tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        imgPhoto = findViewById(R.id.img_photo_tv_show);
        tvTitle = findViewById(R.id.tv_title_tv_show);
        tvDate = findViewById(R.id.tv_date_tv_show);
        tvCreator = findViewById(R.id.tv_creator_tv_show);
        tvOverview = findViewById(R.id.tv_overview_tv_show);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV);
        showData(tvShow);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(tvShow.getTitle());
        }
    }

    private void showData(TvShow tvShow) {
        Glide.with(this).load(tvShow.getPhoto()).into(imgPhoto);
        tvTitle.setText(tvShow.getTitle());
        tvDate.setText(tvShow.getReleaseDate());
        tvCreator.setText(tvShow.getCreator());
        tvOverview.setText(tvShow.getOverview());
    }
}
