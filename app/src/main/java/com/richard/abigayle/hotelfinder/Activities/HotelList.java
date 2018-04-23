package com.richard.abigayle.hotelfinder.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.richard.abigayle.hotelfinder.Helpers.HotelDao;
import com.richard.abigayle.hotelfinder.Helpers.Hotels;
import com.richard.abigayle.hotelfinder.R;
import com.richard.abigayle.hotelfinder.UiHelpers.HotelAdapter;
import com.richard.abigayle.hotelfinder.UiHelpers.HotelListViewModel;

import java.util.ArrayList;
import java.util.List;

public class HotelList extends AppCompatActivity {
    RecyclerView recyclerView;

    HotelDao hotelDaol;
    List<Hotels> hotelsList;
    private HotelListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        final HotelAdapter hotelAdapter = new HotelAdapter(this);

        recyclerView = findViewById(R.id.recycle_view);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(hotelAdapter);
        viewModel = ViewModelProviders.of(this).get(HotelListViewModel.class);
        viewModel.getAllHotel().observe(this, new Observer<List<Hotels>>() {
            @Override
            public void onChanged(@Nullable List<Hotels> hotels) {
                hotelAdapter.setHotels(hotels);

            }
        });





    }
}
