package com.richard.abigayle.hotelfinder.Activities;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.richard.abigayle.hotelfinder.Helpers.Hotels;
import com.richard.abigayle.hotelfinder.R;
import com.richard.abigayle.hotelfinder.UiHelpers.DetailsActivityViewModel;

import org.json.JSONException;

public class HotelDetailsActivity extends LifecycleActivity implements OnMapReadyCallback{
    DetailsActivityViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        Log.d("shoot","Got to HotelDetailClass");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    void bindDataToUi(Hotels hotels){
        TextView hotelName = (TextView)findViewById(R.id.hotel_name);
        TextView hotelAddress = (TextView)findViewById(R.id.hotel_address);

        hotelName.setText(hotels.placeName);
        hotelAddress.setText(hotels.placeAd);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Sydney in the Map"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
