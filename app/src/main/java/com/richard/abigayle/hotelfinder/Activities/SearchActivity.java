package com.richard.abigayle.hotelfinder.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.preference.DialogPreference;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.richard.abigayle.hotelfinder.R;

public class SearchActivity extends AppCompatActivity  {
    PlaceAutocompleteFragment autocompleteFragment;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year,month,day;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);




        autocompleteFragment = (PlaceAutocompleteFragment)getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(SearchActivity.this,"Selected Place is "+ place.getLatLng(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Status status) {

                Toast.makeText(SearchActivity.this,status.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    @SuppressWarnings("deprecation")
    public void CheckOut(View view){
        showDialog(1);
    }
    @SuppressWarnings("deprecation")
    public void CheckIn(View view){
        showDialog(2);
    }
    public void room(View view){
        showDialog(3);
    }
    public void adult(View view){
        showDialog(4);
    }
    public void children(View view){
        showDialog(5);
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected Dialog onCreateDialog(int id){
        DatePickerDialog date = new DatePickerDialog(this,myDateListerner,year,month,day);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        switch (id){
            case 1:

                date.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                date.getDatePicker().setMaxDate(System.currentTimeMillis() + 1000*60*60*24*14);

                return date;
            case 2:

                date.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                return date;
            case 3:

                builder.setTitle("Rooms")
                        .setItems(R.array.Rooms, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        });
                return builder.create();
            case 4:

                builder.setTitle("Rooms")
                        .setItems(R.array.Rooms, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        });
                return builder.create();
            case 5:

                builder.setTitle("Rooms")
                        .setItems(R.array.Rooms, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        });
                return builder.create();




        }

        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListerner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {

                year = selectedYear;
                month = selectedMonth + 1;
                day = selectedDay;
            Toast.makeText(SearchActivity.this, " Year is " +year + month + day,Toast.LENGTH_LONG).show();





        }
    };

    private void showdDate(int year, int month, int day){
        String ate = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        Toast.makeText(SearchActivity.this,ate,Toast.LENGTH_LONG).show();

    }



}
