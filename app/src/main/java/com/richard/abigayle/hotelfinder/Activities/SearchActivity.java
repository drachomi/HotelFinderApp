package com.richard.abigayle.hotelfinder.Activities;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.preference.DialogPreference;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.richard.abigayle.hotelfinder.Helpers.HotelRepository;
import com.richard.abigayle.hotelfinder.R;
import com.richard.abigayle.hotelfinder.UiHelpers.HotelListViewModel;

import java.util.Map;
import java.util.Vector;

public class SearchActivity extends AppCompatActivity  {
    PlaceAutocompleteFragment autocompleteFragment;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year,month,day;
    private String location = "";
    private String curLocation = "";


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Bundle bundle = getIntent().getExtras();
        curLocation = bundle.getString("location");


        autocompleteFragment = (PlaceAutocompleteFragment)getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setHint("Enter a Location");
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                //Toast.makeText(SearchActivity.this,"Selected Place is "+ place.getLatLng(),Toast.LENGTH_LONG).show();
                Log.d("location", "location is "+place.getLatLng());
                String locationString = place.getLatLng().toString();

                Log.d("mainlocation",locationString);

                Button button = findViewById(R.id.search_btn);
                button.setText(place.getName());



                location = locationString.substring(10,locationString.length()-1);
                Log.d("mainlocation",location);



            }

            @Override
            public void onError(Status status) {

                Toast.makeText(SearchActivity.this,status.toString(),Toast.LENGTH_LONG).show();

            }
        });

    }
    public void searchClick(View view){

        if(location.equals("")){
            Toast.makeText(SearchActivity.this,"Please pick your location",Toast.LENGTH_LONG).show();

        }
        else {
            Log.d("location","Called the repository");

            search(location);
        }

    }
    public void curLocation(View view){
        search(curLocation);
    }

    private void search(String mLocate){
        Intent intent = new Intent(this,SplashScreen.class);
        intent.putExtra("location",mLocate);
        startActivity(intent);

    }




}
