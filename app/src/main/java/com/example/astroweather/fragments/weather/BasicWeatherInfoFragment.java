package com.example.astroweather.fragments.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.astroweather.R;
import com.example.astroweather.adapters.ForecastInfoItem;
import com.example.astroweather.data.DataSettings;
import com.example.astroweather.data.WeatherInfo;
import com.example.astroweather.files.JSONUtils;

public class BasicWeatherInfoFragment extends Fragment {

    private ImageView weather_image;
    private TextClock clock;
    private TextView city;
    private TextView coordinates_info;
    private TextView temp;
    private TextView pressure;
    private TextView description;

    private WeatherInfo weatherInfo;
    private DataSettings dataSettings;

    public BasicWeatherInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_weather_info, container, false);
        initData();
        initLayout(view);
        return view;
    }

    private void initData() {
        dataSettings = DataSettings.getInstance();
        weatherInfo = JSONUtils.getWeatherInfoFromJSONArray(this.getContext());
    }

    private void initLayout(View view) {
        weather_image = view.findViewById(R.id.weather_image);
        clock = view.findViewById(R.id.clock);
        city = view.findViewById(R.id.city);
        coordinates_info = view.findViewById(R.id.coordinates_info);
        temp = view.findViewById(R.id.temp);
        pressure = view.findViewById(R.id.pressure);
        description = view.findViewById(R.id.description);

        weather_image.setImageResource(getContext().getResources().getIdentifier(weatherInfo.getImageId(), "drawable", getContext().getPackageName()));
        city.setText(weatherInfo.getCityName());
        coordinates_info.setText(weatherInfo.getLatitude() + "\n" + weatherInfo.getLongitude());
        pressure.setText(weatherInfo.getPressure());
        description.setText(weatherInfo.getDescription());


        float tmp = weatherInfo.getTemperature();
        String result = new String();
        if(dataSettings.units.equals("°C")) {
            tmp -= 273.15f;
        } else if (dataSettings.units.equals("F")){
            tmp = tmp * (9.0f/5.0f) - 459.67f;
        }
        temp.setText(String.valueOf(tmp) + dataSettings.units);
    }

}