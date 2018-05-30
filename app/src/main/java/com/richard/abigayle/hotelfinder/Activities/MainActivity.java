package com.richard.abigayle.hotelfinder.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.richard.abigayle.hotelfinder.Activities.DetailsActivity.HotelDetailsActivity;
import com.richard.abigayle.hotelfinder.R;

public class MainActivity extends AppCompatActivity {

    final int MY_ACCESS_COARSE_LOCATION = 200;
    private FusedLocationProviderClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        getLocation();


    }


    @SuppressLint("MissingPermission")
    void getLocation() {

        mLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                      //  Log.d("location",location.toString());
                        if (location != null) {

                            String mlocation = location.getLatitude() + "," + location.getLongitude();
                            Log.d("location", mlocation);
                            Log.d("location", "About to create repo");
//                            HotelRepository hotelRepository = new HotelRepository((Application) getApplicationContext());
//                            hotelRepository.scanFetch(mlocation);
                            Log.d("location", "finished scan");



//                            Intent i = new Intent(MainActivity.this, HotelDetailsActivity.class);
//                            startActivity(i);

                        }
                        if (location == null) {
                            Log.d("location", "Getting null response");
                            Toast.makeText(MainActivity.this, "LOCATEION IS NUll", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        Intent i = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(i);
        }


    }


