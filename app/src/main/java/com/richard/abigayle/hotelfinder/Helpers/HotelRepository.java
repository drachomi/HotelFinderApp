package com.richard.abigayle.hotelfinder.Helpers;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.richard.abigayle.hotelfinder.AppExecutors;
import com.richard.abigayle.hotelfinder.POJO.DistanceBetween;
import com.richard.abigayle.hotelfinder.POJO.MainResponse;
import com.richard.abigayle.hotelfinder.R;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LENOVO on 3/26/2018.
 */

public class HotelRepository {

    private static final String LOG_TAG = HotelRepository.class.getSimpleName();
    GeoDataClient mGoogleApiClient;


    private LiveData<List<Hotels>> mHotels;
    private static final Object LOCK = new Object();
    private static HotelRepository sInstance;
    private  HotelDao mHotelDao;
    private Context context;
    private boolean isFinished = false;
    String root = Environment.getExternalStorageDirectory().toString();
    File myDir = new File(root + "/hotel_images");



    private boolean mInitialized = false;


    public HotelRepository(Application application) {
        HotelDatabase hotelDb = HotelDatabase.getInstance(application);

        mHotelDao = hotelDb.hotelDao();
        mHotels = mHotelDao.getAll();
        context = application;
    }
    public LiveData<List<Hotels>>getAllHotels(){
        return mHotels;
    }





    private boolean isFetchedNeeded() {
        return true;
    }

    //TODO Create method that scans with nextpage tokener
    public void scanFetch(String location) {
//        deleteOldData();

        String key = "AIzaSyBWKQHS39-SYUNxEEAry1FxrMET2NwhqxE";
        Log.d("location","Got to Repository scanFetchMethod");
        Map<String, String> param = new HashMap<>();
        param.put("location", location);
        param.put("key", key);
        param.put("type", "lodging");
        Log.d("location"," added param  ");
        param.put("radius", "20000");

        Log.d("location","added to map ");
        Log.d("location","location is " + location);




        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        Log.d("location","Finished building retrofit builder ");

        HotelNetworkClient networkClient = retrofit.create(HotelNetworkClient.class);
        Log.d("location","finished creating HotelClient");
        Call<MainResponse> call = networkClient.hotelList(param);
            Log.d("location","Calling client ");
        Log.d("location",location);


        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                Log.d("location","Got response from retrofit");
                Log.d("location",response.body().toString());
                String token = response.body().getNext_page_token();
                if(response.isSuccessful()){
                    Log.d("location","response successful");
                    if(response.body()==null){
                        Log.d("location","response is null");
                    }
                    else {
                        Log.d("location","response is not null");
                    }
                }
                if(response.body()==null){
                    Log.d("location","response is null");
                }
                else {
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        String place_id = response.body().getResults().get(i).getPlaceId();
                        Log.d("ada", place_id);
                        Log.d("dir", "making dir");
                        if(i==18){
                            nextPage(token);
                        }

                        if(!myDir.exists()){
                            myDir.mkdirs();
                            Log.d("dir","finished making : "+ myDir.getAbsolutePath());
                        }

                        getHotelDetails(place_id, i);
                    }
                }


            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {

            }
        });
    }

    public void nextPage(String tokener){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory( GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();

        HotelNetworkClient networkClient = retrofit.create(HotelNetworkClient.class);

        Call<MainResponse> call = networkClient.nextPage(tokener);

        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                Log.d("location","Got response from retrofit");
                Log.d("location",response.body().toString());
                if(response.isSuccessful()){
                    Log.d("location","response successful");
                    if(response.body()==null){
                        Log.d("location","response is null");
                    }
                    else {
                        Log.d("location","response is not null");
                    }
                }
                if(response.body()==null){
                    Log.d("location","response is null");
                }
                else {
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        String place_id = response.body().getResults().get(i).getPlaceId();
                        Log.d("ada", place_id);
                        Log.d("dir", "making dir");

                        if(!myDir.exists()){
                            myDir.mkdirs();
                            Log.d("dir","finished making : "+ myDir.getAbsolutePath());
                        }

                        getHotelDetails(place_id, 19+i);
                    }
                }

            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {

            }
        });


    }


    private void getHotelDetails(String id, int i) {
        //TODO Scrap this details task use retrofit to get the hotel details.




        mGoogleApiClient = Places.getGeoDataClient(context, null);

        mGoogleApiClient.getPlaceById(id).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse bufferResponse = task.getResult();
                    Place place = bufferResponse.get(0);


                    String place_id = place.getId();

                    String place_name = place.getName().toString();
                    String telephone = place.getPhoneNumber().toString();
                    String address = place.getAddress().toString();
                    String website;
                    if (place.getWebsiteUri() != null) {
                        website = place.getWebsiteUri().toString();
                    } else {
                        website = "www.jw.org";
                    }


                    String latlong = place.getLatLng().toString();
                    int pricelevel = place.getPriceLevel();
                    float rating = place.getRating();


                    getPhoto(place_id);


                    Log.d("eluwa", place.getName().toString());
                    Hotels hotels = new Hotels(i,place_id,place_name,telephone,address,website,pricelevel,rating,latlong,null,null,null,null,null);

                    insertToDb(hotels);




                    bufferResponse.release();


                } else {
                    Log.d("eluwa", "place not found");

                }
            }
        });

    }


    public void getPhoto(String place_id ) {
        mGoogleApiClient = Places.getGeoDataClient(context, null);
        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGoogleApiClient.getPlacePhotos(place_id);
        getDistance("place_id:ChIJQbZ_dzGSOxARugpHaf3JWN0",place_id,place_id);


        photoMetadataResponse.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                PlacePhotoMetadataResponse metadataResponse = task.getResult();
                PlacePhotoMetadataBuffer photoMetadata = metadataResponse.getPhotoMetadata();

                //Map<String,String> imagestring = new HashMap<>();
                List<String> imagestring = new ArrayList<>();



                        for (int i = 0; i < 3; i++) {

                            final int a = i;
                            final int b = photoMetadata.getCount();
                            if(b>a){
                                PlacePhotoMetadata placePhotoMetadata = photoMetadata.get(i);
                                Task<PlacePhotoResponse> responseTask = mGoogleApiClient.getPhoto(placePhotoMetadata);
                                responseTask.addOnCompleteListener(task1 -> {
                                    PlacePhotoResponse photo = task1.getResult();
                                    Bitmap image1 = (b<a)? BitmapFactory.decodeResource(context.getResources(), R.drawable.hotelroom):photo.getBitmap();

                                    imagestring.add(saveToInternalStorage(image1, place_id, a));


                                });
                            }
                            else {
                                Bitmap image1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.hotelroom);
                                imagestring.add(saveToInternalStorage(image1,place_id,a));
                            }

                        }
                    }
        });


    }

    private String saveToInternalStorage(Bitmap bitmap, String place_id, int a) {
        File mypath = new File(root + "/hotel_images",place_id);
        mypath.mkdir();
        String fname = a + ".jpg";

        File imagepath = new File(root + "/hotel_images/" + place_id+fname );

        try {
           FileOutputStream fos = new FileOutputStream(imagepath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        Log.d("gal", mypath.getAbsolutePath());
        Log.d("confused",imagepath.getAbsolutePath());
        insertImageToDb(place_id,imagepath.getAbsolutePath(),a);

        return imagepath.getAbsolutePath();

        }

    private void getDistance(String origins,String destinations,String place_id){
        String key = "AIzaSyAvXgkHdw8StiKTkbiI2sMip1D_Ru37ZTE";
        Map<String, String> param = new HashMap<>();
        param.put("origins", origins);
        param.put("destinations", "place_id:" + destinations);
        param.put("key", key);


        Retrofit.Builder retrofitBuildr = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuildr.build();

        HotelNetworkClient networkClient = retrofit.create(HotelNetworkClient.class);
        Call<DistanceBetween> call = networkClient.distance(param);
        call.enqueue(new Callback<DistanceBetween>() {
            @Override
            public void onResponse(Call<DistanceBetween> call, Response<DistanceBetween> response) {
                String distance="Some km",duration = "Some km";
                if(response.body().getRows().get(0).getElement().get(0).getStatus().equals("OK")){
                    distance = response.body().getRows().get(0).getElement().get(0).getDistance().getText();
                    duration = response.body().getRows().get(0).getElement().get(0).getDuration().getText();
                }



                updateDistance(place_id,distance,duration);

            }

            @Override
            public void onFailure(Call<DistanceBetween> call, Throwable t) {

            }
        });

    }

    private void insertToDb(Hotels hotels){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                mHotelDao.insertAll(hotels);
                return null;
            }
        }.execute();
    }


    private void insertImageToDb(String place_id, String image,int id){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                switch (id){
                    case 0:
                        mHotelDao.insertImage1(place_id,image);
                    case 1:
                        mHotelDao.insertImage2(place_id,image);
                    case 2:
                        mHotelDao.insertImage3(place_id,image);

                }

                return null;
            }
        }.execute();
    }
    void updateDistance(String place_id,String distance,String duration){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                mHotelDao.insertDistance(place_id,distance,duration);
                Log.d("distace",place_id +" distance" + distance  +" duration" + duration);

                return null;
            }
        }.execute();
    }
    private void deleteOldData() {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                mHotelDao.deleteAll();

                return null;
            }
        }.execute();



    }




}



















