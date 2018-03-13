package com.richard.abigayle.hotelfinder.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.richard.abigayle.hotelfinder.Helpers.HotelAsyncTask;
import com.richard.abigayle.hotelfinder.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String location = "-33.8670522,151.1957362";

        Log.d("ASYNC TASK","ABOUT TO ENTER");

        new HotelAsyncTask().execute(location);
        Log.d("ASYNC TASK","ABOUT TO ENTER");

        Intent i = new Intent(MainActivity.this, SearchActivity.class);

        startActivity(i);

    }
}
