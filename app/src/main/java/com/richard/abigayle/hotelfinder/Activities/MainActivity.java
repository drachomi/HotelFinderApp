package com.richard.abigayle.hotelfinder.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.richard.abigayle.hotelfinder.Helpers.HotelDatabase;
import com.richard.abigayle.hotelfinder.Helpers.HotelRepository;
import com.richard.abigayle.hotelfinder.R;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    final int MY_WRITE_EXTERNAL_STORAGE = 900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_WRITE_EXTERNAL_STORAGE);

        }
        else {
            HotelDatabase hotelDatabase = HotelDatabase.getInstance(this);

            HotelRepository hotelRepository = new HotelRepository(hotelDatabase.hotelDao(),getApplicationContext());

            hotelRepository.scanFetch();


            Intent i = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_WRITE_EXTERNAL_STORAGE :{
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    HotelDatabase hotelDatabase = HotelDatabase.getInstance(this);

                    HotelRepository hotelRepository = new HotelRepository(hotelDatabase.hotelDao(),getApplicationContext());

                    hotelRepository.scanFetch();


                    Intent i = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(i);

                }
            }

        }
    }
}
