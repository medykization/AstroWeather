package com.example.astroweather.adapters;

public class ForecastInfoItem {

    private int imageId;
    private String temperature;
    private String pressure;

    public ForecastInfoItem(int imageId, String temperature, String pressure) {
        this.imageId = imageId;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

}
