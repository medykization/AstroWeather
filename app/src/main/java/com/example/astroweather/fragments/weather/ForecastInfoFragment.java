package com.example.astroweather.fragments.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astroweather.R;
import com.example.astroweather.adapters.ForecastInfoItem;
import com.example.astroweather.adapters.ForecastViewAdapter;
import com.example.astroweather.data.DataSettings;
import com.example.astroweather.data.WeatherExtendInfo;
import com.example.astroweather.data.WeatherInfo;
import com.example.astroweather.files.JSONUtils;

import java.util.ArrayList;
import java.util.List;


public class ForecastInfoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ForecastInfoItem> items = new ArrayList<>();
    private DataSettings dataSettings;

    public ForecastInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast_info, container, false);
        initData();
        initLayout(view);
        return view;
    }

    private void initData() {
        dataSettings = DataSettings.getInstance();
        List<WeatherInfo> weatherInfos = JSONUtils.getWeatherInfosFromJSONArray(this.getContext());
        for(WeatherInfo val : weatherInfos) {
            float tmp = val.getTemperature();
            if(dataSettings.units.equals("°C")) {
                tmp -= 273.15f;
            } else if (dataSettings.units.equals("F")){
                tmp = tmp * (9.0f/5.0f) - 459.67f;
            }
            items.add(new ForecastInfoItem(getContext().getResources().getIdentifier(val.getImageId(), "drawable", getContext().getPackageName()), String.valueOf(tmp), val.getPressure()));
        }
    }

    private void initLayout(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mAdapter = new ForecastViewAdapter(items);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}