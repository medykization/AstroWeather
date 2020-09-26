package com.example.astroweather.fragments.astro_info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.astroweather.R;
import com.example.astroweather.data.AstroInfo;
import com.example.astroweather.data.DataSettings;


public class SunInfoFragment extends Fragment {

    private ImageView sunImage;
    private TextView sunrise_time;
    private TextView sunrise_azim;
    private TextView sunset_time;
    private TextView sunset_azim;
    private TextView twilight_time;
    private TextView dawn_time;


    private DataSettings dataSettings;
    private AstroInfo astroInfo;

    public SunInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sun_info, container, false);
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
        sunImage = view.findViewById(R.id.sunImage);
        sunrise_time = view.findViewById(R.id.sunrise_time);
        sunrise_azim = view.findViewById(R.id.sunrise_azim);
        sunset_time = view.findViewById(R.id.sunset_time);
        sunset_azim = view.findViewById(R.id.sunset_azim);
        twilight_time = view.findViewById(R.id.twilight_time);
        dawn_time = view.findViewById(R.id.dawn_time);

        sunImage.setImageResource(getContext().getResources().getIdentifier("r01d", "drawable", getContext().getPackageName()));
    }

    private void refreshData() {
        updateTextFields();
        final int time = dataSettings.delay*1000*60;
        final Handler sunHandler = new Handler();
        sunHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sunHandler.postDelayed(this,time);
                astroInfo = new AstroInfo(dataSettings.longitude, dataSettings.latitude);
                updateTextFields();
            }
        },time);
    }

    private void updateTextFields() {
        String[] layoutValues = astroInfo.getSunInfo();
        sunrise_time.setText(layoutValues[0]);
        sunrise_azim.setText(layoutValues[1]);
        sunset_time.setText(layoutValues[2]);
        sunset_azim.setText(layoutValues[3]);
        twilight_time.setText(layoutValues[4]);
        dawn_time.setText(layoutValues[5]);
    }

}