package com.richard.abigayle.hotelfinder.Activities;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.richard.abigayle.hotelfinder.Helpers.Hotels;
import com.richard.abigayle.hotelfinder.R;
import com.richard.abigayle.hotelfinder.UiHelpers.DetailsActivityViewModel;

import org.json.JSONException;

public class HotelDetailsActivity extends LifecycleActivity {
    DetailsActivityViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        Log.d("shoot","Got to HotelDetailClass");


        mViewModel = ViewModelProviders.of(this).get(DetailsActivityViewModel.class);

        mViewModel.getHotels().observe(this,hotel->{
            if(hotel!=null)bindDataToUi(hotel);
        });


    }

    void bindDataToUi(Hotels hotels){
        TextView hotelName = (TextView)findViewById(R.id.hotel_name);
        TextView hotelAddress = (TextView)findViewById(R.id.hotel_address);

        hotelName.setText(hotels.placeName);
        hotelAddress.setText(hotels.placeAd);



    }
}
