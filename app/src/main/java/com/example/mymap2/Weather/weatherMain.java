package com.example.mymap2.Weather;

import com.google.gson.annotations.SerializedName;

public class weatherMain {
    @SerializedName("temp")
    double mTemp;
    @SerializedName("temp_min")
    double mTempMax;
    @SerializedName("temp_max")
    double mTempMin;

    public double getmTemp() {
        return mTemp;
    }

    public void setmTemp(double mTemp) {
        this.mTemp = mTemp;
    }

    public double getmTempMax() {
        return mTempMax;
    }

    public void setmTempMax(double mTempMax) {
        this.mTempMax = mTempMax;
    }

    public double getmTempMin() {
        return mTempMin;
    }

    public void setmTempMin(double mTempMin) {
        this.mTempMin = mTempMin;
    }
}