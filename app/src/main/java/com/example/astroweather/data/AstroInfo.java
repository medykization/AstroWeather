package com.example.astroweather.data;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;

public class AstroInfo {

    private AstroCalculator astroCalculator;
    private double longitude, latitude;

    public AstroInfo(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private void initCalculator () {
        this.astroCalculator = new AstroCalculator(getDate(),new AstroCalculator.Location(latitude,longitude));
    }

    private AstroDateTime getDate() {
        Calendar calendar = Calendar.getInstance();
        return new AstroDateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), calendar.get(Calendar.ZONE_OFFSET),  true);
    }

    public String[] getMoonInfo() {
        initCalculator();
        AstroCalculator.MoonInfo calculatorMoonInfo = astroCalculator.getMoonInfo();
        String[] moonInfo = new String[6];
        moonInfo[0] = calculatorMoonInfo.getMoonrise().getHour() +":"+ calculatorMoonInfo.getMoonrise().getMinute() +":"+ calculatorMoonInfo.getMoonrise().getSecond();
        moonInfo[1] = calculatorMoonInfo.getMoonset().getHour() +":"+ calculatorMoonInfo.getMoonset().getMinute() +":"+ calculatorMoonInfo.getMoonset().getSecond();
        moonInfo[2] = calculatorMoonInfo.getNextFullMoon().getDay() +"."+ calculatorMoonInfo.getNextFullMoon().getMonth() +"."+ calculatorMoonInfo.getNextFullMoon().getYear();
        moonInfo[3] = calculatorMoonInfo.getNextNewMoon().getDay() +"."+ calculatorMoonInfo.getNextNewMoon().getMonth() +"."+ calculatorMoonInfo.getNextNewMoon().getYear();
        moonInfo[4] = String.valueOf(calculatorMoonInfo.getAge());
        moonInfo[5] = String.valueOf(calculatorMoonInfo.getIllumination()*100);
        return moonInfo;
    }

    public String[] getSunInfo() {
        initCalculator();
        AstroCalculator.SunInfo calculatorSunInfo = astroCalculator.getSunInfo();
        String[] sunInfo = new String[6];
        sunInfo[0] = calculatorSunInfo.getSunrise().getHour() +":"+ calculatorSunInfo.getSunrise().getMinute() +":"+ calculatorSunInfo.getSunrise().getSecond();
        sunInfo[1] = Double.toString(calculatorSunInfo.getAzimuthRise());
        sunInfo[2] = calculatorSunInfo.getSunset().getHour() +":"+ calculatorSunInfo.getSunset().getMinute() +":"+ calculatorSunInfo.getSunset().getSecond();
        sunInfo[3] = Double.toString(calculatorSunInfo.getAzimuthSet());
        sunInfo[4] = calculatorSunInfo.getTwilightEvening().getHour() +":"+ calculatorSunInfo.getTwilightEvening().getMinute() +":"+ calculatorSunInfo.getTwilightEvening().getSecond();
        sunInfo[5] = calculatorSunInfo.getTwilightMorning().getHour() +":"+ calculatorSunInfo.getTwilightMorning().getMinute() +":"+ calculatorSunInfo.getTwilightMorning().getSecond();
        return sunInfo;
    }

}
