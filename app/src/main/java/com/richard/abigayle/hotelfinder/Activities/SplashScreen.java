package com.richard.abigayle.hotelfinder.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.richard.abigayle.hotelfinder.R;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 60000;
    final int MY_WRITE_EXTERNAL_STORAGE = 900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWriteStorage();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,HotelList.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);


    }


    void getWriteStorage(){
        Log.d("location","Entered write storage");
        if (ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_WRITE_EXTERNAL_STORAGE);
            Log.d("location","About to call getLocation after permision check");



        } else if(ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d("location","About to call getLocation No need for permision check");

        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("location","Finished accepting permision");
        switch (requestCode) {
            case MY_WRITE_EXTERNAL_STORAGE : {
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Please accept permissions",Toast.LENGTH_LONG).show();

                }
            }

        }
    }
}
