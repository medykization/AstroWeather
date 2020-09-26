package com.example.astroweather.data;

public class WeatherExtendInfo {

    private String forceOfWind;
    private String windDirection;
    private String humidity;
    private String visibility;

    public WeatherExtendInfo(String forceOfWind, String windDirection, String humidity, String visibility) {
        this.forceOfWind = forceOfWind;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.visibility = visibility;
    }

    public String getForceOfWind() {
        return forceOfWind;
    }

    public void setForceOfWind(String forceOfWind) {
        this.forceOfWind = forceOfWind;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
