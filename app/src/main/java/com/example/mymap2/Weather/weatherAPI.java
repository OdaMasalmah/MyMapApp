package com.example.mymap2.Weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class weatherAPI {
    @SerializedName("Weather")
    List<weather> weather;
    @SerializedName("main")
    weatherMain mMain;

    public weatherMain getmMain() {
        return mMain;
    }

    public void setmMain(weatherMain mMain) {
        this.mMain = mMain;
    }

    public List<com.example.mymap2.Weather.weather> getWeather() {
        return weather;
    }

    public void setWeather(List<com.example.mymap2.Weather.weather> weather) {
        this.weather = weather;
    }
}
