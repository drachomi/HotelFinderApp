package com.richard.abigayle.hotelfinder.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.richard.abigayle.hotelfinder.R;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,HotelList.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);


    }
}
