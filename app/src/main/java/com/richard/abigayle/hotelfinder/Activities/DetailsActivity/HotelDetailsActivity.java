package com.richard.abigayle.hotelfinder.Activities.DetailsActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.richard.abigayle.hotelfinder.Helpers.HotelDao;
import com.richard.abigayle.hotelfinder.Helpers.HotelDatabase;
import com.richard.abigayle.hotelfinder.Helpers.Hotels;
import com.richard.abigayle.hotelfinder.R;
import com.richard.abigayle.hotelfinder.UiHelpers.DetailsActivityViewModel;
import com.richard.abigayle.hotelfinder.UiHelpers.HotelListViewModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.File;

public class HotelDetailsActivity extends AppCompatActivity implements OnMapReadyCallback   {

    CarouselView carouselView;

    DetailsActivityViewModel mViewModel;
   Toolbar actionBar;
//   Intent intent = getIntent();
//   Bundle bundle = intent.getExtras();
//   int position = bundle.getInt("position");

   HotelDao hotelDao;
    Bitmap[] imageToDisplay = new Bitmap[3];
    int intent_postion;




    Hotels mHotels ;
    ImageListener imageListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            intent_postion = extras.getInt("position");
            Toast.makeText(this, "position is " + intent_postion, Toast.LENGTH_SHORT).show();
            Log.d("position", "position is "+intent_postion);

            HotelDatabase hotelDb = HotelDatabase.getInstance(getApplicationContext());
            hotelDao = hotelDb.hotelDao();
            carouselView = findViewById(R.id.carouselView);
             new HotelTask().execute();

        }

        imageListener = (position, imageView) -> imageView.setImageBitmap(imageToDisplay[position]);




//        actionBar = findViewById(R.id.my_toolbar);
//        setSupportActionBar(actionBar);






        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec mSpec = tabHost.newTabSpec("Overview");
        mSpec.setContent(R.id.overview_tabs);
        mSpec.setIndicator("Overview");
        tabHost.addTab(mSpec);

        mSpec = tabHost.newTabSpec("Features");
        mSpec.setContent(R.id.feature_tabs);
        mSpec.setIndicator("Features");
        tabHost.addTab(mSpec);

        mSpec = tabHost.newTabSpec("Map");
        mSpec.setContent(R.id.mapstab);
        mSpec.setIndicator("Map");
        carouselView.setMinimumHeight(150);
        tabHost.addTab(mSpec);



        Log.d("shoot","Got to HotelDetailClass");
       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }






    void bindDataToUi(){
        TextView name,address,telephone,website;
        RatingBar ratingBar;
        ratingBar = findViewById(R.id.details_rating);
        name = findViewById(R.id.details_name);
        address = findViewById(R.id.details_address);
        telephone = findViewById(R.id.details_phone);
        website = findViewById(R.id.details_website);

        name.setText(mHotels.placeName);
        address.setText(mHotels.placeAd);
        telephone.setText(mHotels.telephone);
        website.setText(mHotels.website);
        ratingBar.setRating(mHotels.placeRating);


    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        String[] location = mHotels.latLng.substring(10,mHotels.latLng.length()-1).split(",");
        Double lat = Double.parseDouble(location[0]);
        Double lng = Double.parseDouble(location[1]);


        LatLng sydney = new LatLng(lat,lng);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Sydney in the Map"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }


    private Bitmap getImage(String path){
        File imgFile = new File(path);

        Bitmap myBitmap = null;

        if(imgFile.exists()) {

            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        else {
            myBitmap = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.hotelroom);
        }
        return myBitmap;

    }

    private class HotelTask extends AsyncTask<Void, Void, Hotels> {

        @Override
        protected Hotels doInBackground(Void... voids) {

            mHotels = hotelDao.oneHotel(intent_postion);

            return mHotels;
        }

        @Override
        protected void onPostExecute(Hotels hotels) {
            imageToDisplay[0] = getImage(mHotels.imageId1);
            imageToDisplay[1] = getImage(mHotels.imageId2);
            imageToDisplay[2] = getImage(mHotels.imageId3);



            carouselView = findViewById(R.id.carouselView);
            carouselView.setImageListener(imageListener);
            carouselView.setPageCount(imageToDisplay.length);
            bindDataToUi();




            super.onPostExecute(hotels);
        }
    }

    public void book(View view){
        Uri number = Uri.parse("tel:" + mHotels.telephone);
        Intent intent = new Intent(Intent.ACTION_DIAL,number);
        AlertDialog.Builder builder = new AlertDialog.Builder(HotelDetailsActivity.this);
        View view1 = getLayoutInflater().inflate(R.layout.call_dialog,null);
        Button button = view1.findViewById(R.id.call_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        builder.setView(view1);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
