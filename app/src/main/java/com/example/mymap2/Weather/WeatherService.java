package com.example.mymap2.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("/data/2.5/weather")
    Call<weatherAPI> get(@Query("APPID") String token, @Query("lat") String latitude, @Query("lon") String longitude);
}
