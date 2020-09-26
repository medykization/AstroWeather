package com.example.astroweather.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.astroweather.R;
import com.example.astroweather.adapters.AstroInfoPagerAdapter;

public class AstroInfoActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    private AstroInfoPagerAdapter astroInfoPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro_info);

        initLayout();
    }

    private void initLayout() {
        setViewPager();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setViewPager() {
        viewPager = findViewById(R.id.view_pager);
        astroInfoPagerAdapter = new AstroInfoPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(astroInfoPagerAdapter);
    }
}