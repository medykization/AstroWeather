package com.example.astroweather.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.astroweather.fragments.weather.BasicWeatherInfoFragment;
import com.example.astroweather.fragments.weather.ExtendWeatherInfoFragment;
import com.example.astroweather.fragments.weather.ForecastInfoFragment;

public class WeatherInfoPagerAdapter extends FragmentStateAdapter {

    public WeatherInfoPagerAdapter(@NonNull FragmentManager fm, Lifecycle l) {
        super(fm,l);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BasicWeatherInfoFragment();
            case 1:
                return new ExtendWeatherInfoFragment();
            case 2:
                return new ForecastInfoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
