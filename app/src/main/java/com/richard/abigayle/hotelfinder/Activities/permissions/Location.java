package com.richard.abigayle.hotelfinder.Activities.permissions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.richard.abigayle.hotelfinder.Activities.MainActivity;
import com.richard.abigayle.hotelfinder.Activities.SearchActivity;
import com.richard.abigayle.hotelfinder.R;

public class Location extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_READ_LOCATION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view){

            if( Build.VERSION.BASE_OS.equals(Build.VERSION_CODES.KITKAT)){
                startActivity(new Intent(Location.this,MainActivity.class));

            }
            else {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)){
                        Toast.makeText(this,"PLEASE ACCEPT LOCATION",Toast.LENGTH_LONG).show();
                    }
                    else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_READ_LOCATION);

                    }
                }
                else {
                    startActivity(new Intent(Location.this,MainActivity.class));
                }
            }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_READ_LOCATION:
                startActivity(new Intent(Location.this,MainActivity.class));

        }
        return;
    }
}
