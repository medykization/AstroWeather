package com.example.astroweather.files;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.astroweather.data.DataSettings;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtils {

    public static void updateDateInMemory(Context context) {
        DataSettings dataSettings = DataSettings.getInstance();

        SharedPreferences.Editor editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        editor.putString( "city_name" , dataSettings.cityName);
        editor.putString( "units" , dataSettings.units);
        editor.putInt( "delay" , dataSettings.delay);
        editor.putFloat( "longitude" ,dataSettings.longitude);
        editor.putFloat( "latitude" , dataSettings.latitude);
        editor.commit();
    }

    public static void loadSettingsData(Context context, DataSettings settings) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", MODE_PRIVATE);
        settings.cityName = sharedPreferences.getString("city_name", "łódź");
        settings.units = sharedPreferences.getString("units", "C");
        settings.delay = sharedPreferences.getInt("delay", 10);
        settings.longitude = sharedPreferences.getFloat("longitude", 19.4667f);
        settings.latitude = sharedPreferences.getFloat("latitude", 51.75f);
    }

}
