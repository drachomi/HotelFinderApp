package com.richard.abigayle.hotelfinder.Activities;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.richard.abigayle.hotelfinder.Helpers.HotelDatabase;
import com.richard.abigayle.hotelfinder.Helpers.HotelRepository;
import com.richard.abigayle.hotelfinder.R;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    final int MY_ACCESS_COARSE_LOCATION = 200;
    private FusedLocationProviderClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("location", "Finished accepting permision");
        switch (requestCode) {
            case MY_ACCESS_COARSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Please accept permissions", Toast.LENGTH_LONG).show();
                    getLocation();

                }

            }


        }
    }

    void getLocation() {
        Log.d("location", "Entered Get location");

        Log.d("location", "getting to mLOcationClient.getLatsLocation");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            return;
        }
        mLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d("location",location.toString());
                        if (location != null) {

                            String mlocation = location.getLatitude() + "," + location.getLongitude();
                            Log.d("location", mlocation);
                            Log.d("location", "About to create repo");
//                            HotelRepository hotelRepository = new HotelRepository((Application) getApplicationContext());
//                            hotelRepository.scanFetch(mlocation);
                            Log.d("location", "finished scan");


                            Intent i = new Intent(MainActivity.this, SearchActivity.class);
                            startActivity(i);

                        }
                        if (location == null) {
                            Log.d("location", "Getting null response");
                            Toast.makeText(MainActivity.this, "LOCATEION IS NUll", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        }

    }


