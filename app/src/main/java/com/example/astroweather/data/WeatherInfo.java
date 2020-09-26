package com.example.astroweather.data;

public class WeatherInfo {

    private String imageId;
    private String longitude;
    private String latitude;
    private String cityName;
    private String pressure;
    private String description;
    private String date;
    private float temperature;

    public WeatherInfo(String imageId, String longitude, String latitude, String cityName, String pressure, String description, float temperature, String date) {
        this.imageId = imageId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cityName = cityName;
        this.pressure = pressure;
        this.description = description;
        this.temperature = temperature;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
