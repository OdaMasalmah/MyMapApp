package com.example.mymap2.Weather;

import com.google.gson.annotations.SerializedName;

public class weather {
    @SerializedName("main")
    String mMain;
    @SerializedName("descriotion")
    String mDescriotion;

    public String getmMain() {
        return mMain;
    }

    public void setmMain(String mMain) {
        this.mMain = mMain;
    }

    public String getmDescriotion() {
        return mDescriotion;
    }

    public void setmDescriotion(String mDescriotion) {
        this.mDescriotion = mDescriotion;
    }
}
