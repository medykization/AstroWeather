package com.example.astroweather.data;

import com.example.astroweather.files.SharedPreferencesUtils;

public class DataSettings {

    private static DataSettings instance = null;

    public float longitude;
    public float latitude;
    public int delay;

    public String cityName;
    public String units;

    private DataSettings()
    {

    }

    // static method to create instance of Singleton class
    public static DataSettings getInstance()
    {
        if (instance == null) {
            instance = new DataSettings();
        }
        return instance;
    }

}
