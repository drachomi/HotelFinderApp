package com.richard.abigayle.hotelfinder.Activities;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.richard.abigayle.hotelfinder.Helpers.Hotels;
import com.richard.abigayle.hotelfinder.R;
import com.richard.abigayle.hotelfinder.UiHelpers.DetailsActivityViewModel;

public class HotelDetailsActivity extends LifecycleActivity {
    DetailsActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);


        mViewModel = ViewModelProviders.of(this).get(DetailsActivityViewModel.class);

        mViewModel.getHotels().observe(this,hotel->{
            if(hotel!=null)bindDataToUi(hotel);
        });


    }

    void bindDataToUi(Hotels hotels){

    }
}
