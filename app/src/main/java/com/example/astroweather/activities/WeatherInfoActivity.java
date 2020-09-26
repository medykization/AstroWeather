package com.example.astroweather.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.astroweather.R;
import com.example.astroweather.adapters.WeatherInfoPagerAdapter;

public class WeatherInfoActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    private WeatherInfoPagerAdapter weatherInfoPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        initLayout();
    }

    private void initLayout() {
        setViewPager();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setViewPager() {
        viewPager = findViewById(R.id.view_pager);
        weatherInfoPagerAdapter = new WeatherInfoPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(weatherInfoPagerAdapter);
    }
}