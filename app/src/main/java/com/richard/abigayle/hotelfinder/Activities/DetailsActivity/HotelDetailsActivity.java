package com.richard.abigayle.hotelfinder.Activities.DetailsActivity;

import android.app.ActionBar;
import android.arch.lifecycle.LifecycleActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.richard.abigayle.hotelfinder.R;
import com.richard.abigayle.hotelfinder.UiHelpers.DetailsActivityViewModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HotelDetailsActivity extends AppCompatActivity implements OnMapReadyCallback   {

    CarouselView carouselView;
    int[] imageToDisplay = {R.drawable.pic1,R.drawable.pics2,R.drawable.pics3,R.drawable.hotelroom};
    DetailsActivityViewModel mViewModel;
   Toolbar actionBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

//        actionBar = findViewById(R.id.my_toolbar);
//        setSupportActionBar(actionBar);


        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(imageToDisplay.length);
        carouselView.setImageListener(imageListener);
        Bundle extras = getIntent().getExtras();
        int position;

        if(extras!=null){
             position = extras.getInt("position");
            Toast.makeText(this, "position is " + position, Toast.LENGTH_SHORT).show();
        }



        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec mSpec = tabHost.newTabSpec("Overview");
        mSpec.setContent(R.id.firsttab);
        mSpec.setIndicator("Overview");
        tabHost.addTab(mSpec);

        mSpec = tabHost.newTabSpec("Features");
        mSpec.setContent(R.id.secondtab);
        mSpec.setIndicator("Features");
        tabHost.addTab(mSpec);

        mSpec = tabHost.newTabSpec("Map");
        mSpec.setContent(R.id.thirdtab);
        mSpec.setIndicator("Map");
        carouselView.setMinimumHeight(150);
        tabHost.addTab(mSpec);



        Log.d("shoot","Got to HotelDetailClass");
       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    ImageListener imageListener = (position, imageView) -> imageView.setImageResource(imageToDisplay[position]);

//    void bindDataToUi(Hotels hotels){
//        TextView hotelName = (TextView)findViewById(R.id.hotel_name);
//        TextView hotelAddress = (TextView)findViewById(R.id.hotel_address);
//
//        hotelName.setText(hotels.placeName);
//        hotelAddress.setText(hotels.placeAd);
//
//
//
//    }
//
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Sydney in the Map"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }


}
