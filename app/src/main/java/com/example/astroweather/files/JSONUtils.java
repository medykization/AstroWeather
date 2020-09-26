package com.example.astroweather.files;

import android.content.Context;

import com.example.astroweather.data.DataSettings;
import com.example.astroweather.data.WeatherExtendInfo;
import com.example.astroweather.data.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JSONUtils {

    public static void saveWeatherInfo (JSONObject object, Context context) {
        try {
            FileOutputStream fOut = context.openFileOutput("weather.json",Context.MODE_PRIVATE);
            fOut.write(object.toString().getBytes());
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<WeatherInfo> getWeatherInfosFromJSONArray (Context context) {
        JSONObject object = getJSONWeatherObject(context);
        JSONArray array = null;
        try {
            array = object.getJSONArray("list");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<WeatherInfo> weatherInfos = new ArrayList<>();
        List<JSONObject> objects = new ArrayList<>();

        for(int i = 0; i < array.length(); i += 8) {
            try {
                objects.add(array.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DataSettings dataSettings = DataSettings.getInstance();
        for(JSONObject val : objects) {
            try {
                weatherInfos.add(new WeatherInfo(
                        "r" + val.getJSONArray("weather").getJSONObject(0).getString("icon"),
                        String.valueOf(object.getJSONObject("city").getJSONObject("coord").getDouble("lon")),
                        String.valueOf(object.getJSONObject("city").getJSONObject("coord").getDouble("lat")),
                        dataSettings.cityName,
                        val.getJSONObject("main").getString("pressure"),
                        val.getJSONArray("weather").getJSONObject(0).getString("description"),
                        (float) val.getJSONObject("main").getDouble("temp"),
                        val.getString("dt_txt")
                ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(weatherInfos.size() == 0) {
            for(int i = 0; i< 5; i++) {
                weatherInfos.add(new WeatherInfo("none", "0", "0", "none", "0", "none", 0f, "none"));
            }
        }
        return weatherInfos;
    }


    public static WeatherInfo getWeatherInfoFromJSONArray (Context context) {
        JSONObject object = getJSONWeatherObject(context);
        JSONArray array = null;
        try {
            array = object.getJSONArray("list");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WeatherInfo weatherInfo = null;
        DataSettings dataSettings = DataSettings.getInstance();
        try {
            weatherInfo = new WeatherInfo(
                    "r" + array.getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("icon"),
                    String.valueOf(object.getJSONObject("city").getJSONObject("coord").getDouble("lon")),
                    String.valueOf(object.getJSONObject("city").getJSONObject("coord").getDouble("lat")),
                    dataSettings.cityName,
                    array.getJSONObject(0).getJSONObject("main").getString("pressure"),
                    array.getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description"),
                    (float) array.getJSONObject(0).getJSONObject("main").getDouble("temp"),
                    array.getJSONObject(0).getString("dt_txt"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
        List<JSONObject> jsonObjects = new ArrayList<>();
        String date = getParseDate(new Date());
        for(int i = 0; i < array.length(); i++) {
            try {
                JSONObject tmp = array.getJSONObject(i);
                String weatherDate = getParseDate(new Date(tmp.getLong("dt")));
                if(weatherDate.compareTo(date) <= 0) {
                    jsonObjects.add(tmp);
                }
                else
                    break;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DataSettings dataSettings = DataSettings.getInstance();
        for(JSONObject val : jsonObjects) {
            try {
                long hour = TimeUnit.MILLISECONDS.toHours(val.getLong("dt"));

                if() {
                    weatherInfo = new WeatherInfo(
                            "r" + val.getJSONArray("weather").getJSONObject(0).getString("icon"),
                            String.valueOf(object.getJSONObject("city").getJSONObject("coord").getDouble("lon")),
                            String.valueOf(object.getJSONObject("city").getJSONObject("coord").getDouble("lat")),
                            dataSettings.cityName,
                            val.getJSONObject("main").getString("pressure"),
                            val.getJSONArray("weather").getJSONObject(0).getString("description"),
                            (float) val.getJSONObject("main").getDouble("temp")
                    );
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
         */

        if(weatherInfo == null)
            weatherInfo = new WeatherInfo("none", "0", "0", "none", "0", "none", 0f, "none");

        return weatherInfo;
    }


    public static WeatherExtendInfo getWeatherExtendInfoFromJSONArray (Context context) {
        JSONArray array = getJSONWeatherArray(context);
        WeatherExtendInfo weatherExtendInfo = null;

        try {
            weatherExtendInfo = new WeatherExtendInfo(
                    array.getJSONObject(0).getJSONObject("wind").getDouble("speed")+"km/h",
                    String.valueOf(array.getJSONObject(0).getJSONObject("wind").getInt("deg")),
                    array.getJSONObject(0).getJSONObject("main").getString("humidity"),
                    array.getJSONObject(0).getString("visibility")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
        List<JSONObject> jsonObjects = new ArrayList<>();
        Date date = new Date();
        for(int i = 0; i < array.length(); i++) {
            JSONObject tmp = null;
            try {
                tmp = array.getJSONObject(i);
                Date weatherDate = new Date(tmp.getLong("dt"));
                if(date.getDay() == weatherDate.getDay())
                    jsonObjects.add(tmp);
                else
                    break;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for(JSONObject val : jsonObjects) {
            try {
                Date hour = new Date(val.getLong("dt"));
                if(hour.getHours() >= date.getHours()) {
                    weatherExtendInfo = new WeatherExtendInfo(
                            val.getJSONObject("wind").getDouble("speed")+"km/h",
                            String.valueOf(val.getJSONObject("wind").getInt("deg")),
                            val.getJSONObject("main").getString("humidity"),
                            val.getString("visibility")
                    );
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
         */
        if(weatherExtendInfo == null)
            weatherExtendInfo = new WeatherExtendInfo("0", "0", "0", "0");
        return weatherExtendInfo;
    }


    public static JSONArray getJSONWeatherArray (Context context) {
        JSONArray result = null;
        try {
            FileInputStream fin = context.openFileInput("weather.json");
            int size = fin.available();
            byte[] buffer = new byte[size];
            fin.read(buffer);
            fin.close();
            result = new JSONObject(new String(buffer)).getJSONArray("list");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject getJSONWeatherObject (Context context) {
        JSONObject result = null;
        try {
            FileInputStream fin = context.openFileInput("weather.json");
            int size = fin.available();
            byte[] buffer = new byte[size];
            fin.read(buffer);
            fin.close();
            result = new JSONObject(new String(buffer));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getParseDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        return formatter.format(date);
    }

    private static String getParseTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        return formatter.format(date);
    }

}
