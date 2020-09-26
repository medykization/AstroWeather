package com.example.astroweather.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.astroweather.R;
import com.example.astroweather.connection.ConnectWeatherApi;
import com.example.astroweather.connection.InternetConnection;
import com.example.astroweather.data.DataSettings;
import com.example.astroweather.files.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;

    private Button weather_button;
    private Button astro_button;
    private Button settings_button;
    private Button credits_button;

    private DataSettings dataSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        loadSettingsData();
        updateData();
    }

    private void updateData() {
        if(InternetConnection.isInternetConnectionAvailable(this.getApplicationContext())) {
            System.out.println("start");
            ConnectWeatherApi.getWeatherInfo(this.getApplicationContext());
        } else {
            Toast.makeText(this.getApplicationContext(), "No Internet Access", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSettingsData() {
        dataSettings = DataSettings.getInstance();
        SharedPreferencesUtils.loadSettingsData(this.getApplicationContext(), dataSettings);
    }

    private void initLayout() {
        findLayoutElements();
        setListenersToButtons();
        setTextToLayoutElements();
    }

    private void findLayoutElements() {
        title = findViewById(R.id.title);
        weather_button = findViewById(R.id.weather_button);
        astro_button = findViewById(R.id.astro_button);
        settings_button = findViewById(R.id.settings_button);
        credits_button = findViewById(R.id.credits_button);
    }

    private void setListenersToButtons() {
        weather_button.setOnClickListener(this);
        astro_button.setOnClickListener(this);
        settings_button.setOnClickListener(this);
        credits_button.setOnClickListener(this);
    }

    private void setTextToLayoutElements() {
        setTextToTextView("AstroWeather", title);
    }

    private void setTextToTextView(String text, TextView textView) {
        textView.setText(text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weather_button:
                startActivity(new Intent(this.getApplicationContext(), WeatherInfoActivity.class));
                break;
            case R.id.astro_button:
                startActivity(new Intent(this.getApplicationContext(), AstroInfoActivity.class));
                break;
            case R.id.settings_button:
                startActivity(new Intent(this.getApplicationContext(), SettingsActivity.class));
                break;
            case R.id.credits_button:
                startActivity(new Intent(this.getApplicationContext(), CreditsActivity.class));
                break;
        }

    }


}