package com.example.mymap2;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mymap2.Weather.WeatherService;
import com.example.mymap2.Weather.weatherAPI;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    TextView tx1, tx2, tx3;
    LatLng let;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        tx1 = (TextView) findViewById(R.id.t1);
        tx2 = (TextView) findViewById(R.id.t2);
        tx3 = (TextView) findViewById(R.id.t3);
        LatLng ln = new LatLng(31.9, 35.2);
        let = ln;
        mMap.addMarker(new MarkerOptions().position(ln));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng));
                tx1.setText(latLng.toString());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 4);
                mMap.animateCamera(cameraUpdate);

                Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                let = latLng;
                try {
                    List<Address> addresses = gcd.getFromLocation((double) latLng.latitude, (double) latLng.longitude, 1);
                    tx2.setText(addresses.get(0).getAddressLine(0));
                    System.out.println(addresses.get(0).getAddressLine(0));
                    fetchWeather();
                } catch(Exception ex) {

                }
            }
        });


    }
    private void fetchWeather() {
        Retrofit ret = new Retrofit.Builder().baseUrl("https://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();
        WeatherService service = ret.create(WeatherService.class);
        // run request
        service.get("467adf7590503f0a94576ae678367ff0", let.latitude+"", let.longitude+"").enqueue(new Callback<weatherAPI>() {
            @Override
            public void onResponse(Call<weatherAPI> call, Response<weatherAPI> response) {
                System.out.println((response.body().getmMain().getmTemp() - 273));
                tx3.setText((response.body().getmMain().getmTemp() - 273) + "");
            }
            @Override
            public void onFailure(Call<weatherAPI> call, Throwable t) {
                Log.d("REPONSEFAIL", t.getMessage());
            }
        });

    }
}
