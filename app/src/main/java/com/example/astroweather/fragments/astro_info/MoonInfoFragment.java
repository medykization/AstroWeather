package com.example.astroweather.fragments.astro_info;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.astroweather.R;
import com.example.astroweather.data.AstroInfo;
import com.example.astroweather.data.DataSettings;


public class MoonInfoFragment extends Fragment {

    private ImageView moonImage;
    private TextView moonrise_time;
    private TextView moonset_time;
    private TextView full_moon;
    private TextView moon_new;
    private TextView phase;
    private TextView synodic_month_day;

    private DataSettings dataSettings;
    private AstroInfo astroInfo;

    public MoonInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moon_info, container, false);
        initLayout(view);
        initData();
        refreshData();
        return view;
    }

    private void initData() {
        dataSettings = DataSettings.getInstance();
        astroInfo = new AstroInfo(dataSettings.longitude, dataSettings.latitude);
    }

    private void initLayout(View view) {
        moonImage = view.findViewById(R.id.moonImage);
        moonrise_time = view.findViewById(R.id.moonrise_time);
        moonset_time = view.findViewById(R.id.moonset_time);
        full_moon = view.findViewById(R.id.full_moon);
        moon_new = view.findViewById(R.id.moon_new);
        phase = view.findViewById(R.id.phase);
        synodic_month_day = view.findViewById(R.id.synodic_month_day);

        moonImage.setImageResource(getContext().getResources().getIdentifier("r01n", "drawable", getContext().getPackageName()));
    }

    private void refreshData() {
        updateTextFields();
        final int time = dataSettings.delay*1000*60;
        final Handler moonHandler = new Handler();
        moonHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moonHandler.postDelayed(this,time);
                astroInfo = new AstroInfo(dataSettings.longitude, dataSettings.latitude);
                updateTextFields();
            }
        },time);
    }

    private void updateTextFields() {
        String[] layoutValues = astroInfo.getMoonInfo();

        moonrise_time.setText(layoutValues[0]);
        moonset_time.setText(layoutValues[1]);
        full_moon.setText(layoutValues[2]);
        moon_new.setText(layoutValues[3]);
        phase.setText(layoutValues[4]);
        synodic_month_day.setText(layoutValues[5]);
    }

}