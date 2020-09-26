package com.example.astroweather.connection;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroweather.data.DataSettings;
import com.example.astroweather.files.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class ConnectWeatherApi {
    public static void getWeatherInfo(final Context context) throws IllegalArgumentException {
        DataSettings dataSettings = DataSettings.getInstance();
        System.out.println(dataSettings.cityName);
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + dataSettings.cityName + "&appid=918cb336d7bfecd88a54f5c89a61ba93";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(!response.getString("cod").equals("200"))
                                throw new IllegalArgumentException();
                            JSONUtils.saveWeatherInfo(response, context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Incorrect city name", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);

    }
}
