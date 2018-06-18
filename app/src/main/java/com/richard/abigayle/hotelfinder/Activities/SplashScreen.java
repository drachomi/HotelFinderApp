package com.richard.abigayle.hotelfinder.Activities;

import android.Manifest;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.richard.abigayle.hotelfinder.Helpers.HotelDao;
import com.richard.abigayle.hotelfinder.Helpers.HotelDatabase;
import com.richard.abigayle.hotelfinder.Helpers.HotelRepository;
import com.richard.abigayle.hotelfinder.R;
import com.richard.abigayle.hotelfinder.UiHelpers.HotelListViewModel;

public class SplashScreen extends AppCompatActivity {
    private  final int SPLASH_TIME_OUT = 1000;
    final int MY_WRITE_EXTERNAL_STORAGE = 900;
    HotelDao hotelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        HotelListViewModel viewModel;

        HotelDatabase hotelDatabase = HotelDatabase.getInstance(this);
        hotelDao = hotelDatabase.hotelDao();

        deleteOldData();


        Bundle bundle = getIntent().getExtras();
        String location = bundle.getString("location");
        HotelRepository hotelRepository = new HotelRepository((Application) getApplicationContext());
        Toast.makeText(this,"location is "+location,Toast.LENGTH_SHORT).show();
        Log.d("location",location);
        hotelRepository.scanFetch(location);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreen.this,HotelList.class);
            startActivity(i);
            finish();
        },SPLASH_TIME_OUT);


    }



    private void deleteOldData() {

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {

                hotelDao.deleteAll();

                return null;
            }
        }.execute();

        return;

    }
}
