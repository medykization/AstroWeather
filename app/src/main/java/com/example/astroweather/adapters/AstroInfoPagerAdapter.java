package com.example.astroweather.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.astroweather.fragments.astro_info.BasicAstroInfoFragment;
import com.example.astroweather.fragments.astro_info.MoonInfoFragment;
import com.example.astroweather.fragments.astro_info.SunInfoFragment;

public class AstroInfoPagerAdapter extends FragmentStateAdapter {

    public AstroInfoPagerAdapter(@NonNull FragmentManager fm, Lifecycle l) {
        super(fm,l);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BasicAstroInfoFragment();
            case 1:
                return new SunInfoFragment();
            case 2:
                return new MoonInfoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
