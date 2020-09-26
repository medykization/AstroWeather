package com.example.astroweather.fragments.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.astroweather.R;
import com.example.astroweather.data.WeatherExtendInfo;
import com.example.astroweather.data.WeatherInfo;
import com.example.astroweather.files.JSONUtils;

public class ExtendWeatherInfoFragment extends Fragment {

    private TextView wind_direction;
    private TextView wind_speed;
    private TextView humidity;
    private TextView visibility;

    private WeatherExtendInfo weatherExtendInfo;

    public ExtendWeatherInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_extend_weather_info, container, false);
        initData();
        initLayout(view);
        return view;
    }

    private void initData() {
        weatherExtendInfo = JSONUtils.getWeatherExtendInfoFromJSONArray(this.getContext());
    }

    private void initLayout(View view) {

        wind_direction = view.findViewById(R.id.wind_direction);
        wind_speed = view.findViewById(R.id.wind_speed);
        humidity = view.findViewById(R.id.humidity);
        visibility = view.findViewById(R.id.visibility);

        wind_direction.setText(weatherExtendInfo.getWindDirection());
        wind_speed.setText(weatherExtendInfo.getForceOfWind());
        humidity.setText(weatherExtendInfo.getHumidity() + "%");
        visibility.setText(weatherExtendInfo.getVisibility() + "m");
    }

}