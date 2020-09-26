package com.example.astroweather.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.astroweather.R;
import com.example.astroweather.connection.ConnectWeatherApi;
import com.example.astroweather.data.DataSettings;
import com.example.astroweather.files.SharedPreferencesUtils;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText city_name_input;
    private EditText longitude_input;
    private EditText latitude_input;

    private Spinner units;
    private Spinner delay;

    private Button weather_submit;
    private Button astro_submit;

    private String unit_selected;
    private int delay_selected;

    private DataSettings dataSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initData();
        initLayout();
    }

    private void initData() {
        dataSettings = DataSettings.getInstance();
    }

    private void initLayout() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        delay = findViewById(R.id.delay);
        units = findViewById(R.id.units);
        setSpinnerAdapter(delay, R.array.numbers);
        delay.setSelection(dataSettings.delay - 4);
        setSpinnerAdapter(units, R.array.units);
        units.setSelection(getUnitId(dataSettings.units));


        city_name_input = findViewById(R.id.city_name_input);
        longitude_input = findViewById(R.id.longitude_input);
        latitude_input = findViewById(R.id.latitude_input);
        weather_submit = findViewById(R.id.weather_submit);
        astro_submit = findViewById(R.id.astro_submit);

        city_name_input.setText(dataSettings.cityName, TextView.BufferType.EDITABLE);
        longitude_input.setText(String.valueOf(dataSettings.longitude), TextView.BufferType.EDITABLE);
        latitude_input.setText(String.valueOf(dataSettings.latitude), TextView.BufferType.EDITABLE);

        weather_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSettings.units = unit_selected;
                dataSettings.cityName = city_name_input.getText().toString();
                try {
                    ConnectWeatherApi.getWeatherInfo(getApplicationContext());
                    SharedPreferencesUtils.updateDateInMemory(getApplicationContext());
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "Incorrect city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        astro_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSettings.delay = delay_selected;
                float longitude, latitude;
                longitude = Float.parseFloat(longitude_input.getText().toString());
                latitude = Float.parseFloat(latitude_input.getText().toString());

                if(longitude >= -180 && longitude <= 180 && latitude >= -90 && latitude <= 90) {
                    dataSettings.longitude = longitude;
                    dataSettings.latitude = latitude;
                    SharedPreferencesUtils.updateDateInMemory(getApplicationContext());
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setSpinnerAdapter(Spinner spinner, int array_id) {
        ArrayAdapter<CharSequence> delayAdapter = ArrayAdapter.createFromResource(this,
                array_id, android.R.layout.simple_spinner_item);
        delayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(delayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId() == R.id.units)
            unit_selected =  adapterView.getItemAtPosition(i).toString();

        if(adapterView.getId() == R.id.delay)
            delay_selected = Integer.parseInt(adapterView.getItemAtPosition(i).toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private int getUnitId(String unit) {
        if(unit.equals("K"))
            return 2;
        else if (unit.equals("F"))
            return 1;
        else
            return 0;
    }
}