package com.example.astroweather.fragments.astro_info;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.R;
import com.example.astroweather.data.DataSettings;


public class BasicAstroInfoFragment extends Fragment {

    private TextView coordinates_info;
    private TextClock clock;

    private DataSettings dataSettings;

    public BasicAstroInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_astro_info, container, false);
        initLayout(view);
        initData();
        refreshData();
        return view;
    }

    private void initData() {
        dataSettings = DataSettings.getInstance();
    }

    private void initLayout(View view) {
        coordinates_info = view.findViewById(R.id.coordinates_info);
        clock = view.findViewById(R.id.clock);
    }

    private void refreshData() {
        updateTextFields();
        final int time = dataSettings.delay*1000*60;
        final Handler basicHandler = new Handler();
        basicHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateTextFields();
                basicHandler.postDelayed(this,time);
            }
        },time);
    }

    private void updateTextFields() {
        coordinates_info.setText(dataSettings.latitude + "\n" + dataSettings.longitude);
    }

}