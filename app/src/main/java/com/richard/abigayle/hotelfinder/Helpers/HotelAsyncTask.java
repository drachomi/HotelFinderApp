package com.richard.abigayle.hotelfinder.Helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jaruspace on 2/19/2018.
 */

public class HotelAsyncTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String location = strings[0];
        String streamString = "";

        Log.d("ASYNC TASK", "ABOUT TO BUILD URL");
        URL hotelSearch = NetworkUtils.buildRaderUrl(location);

        Log.d("ASYNC TASK", "FINISHED BUILDING URL");


        try {
            Log.d("ASYNC TASK", "ABOUT TOO GET HTTP CONNECTION");
            HttpURLConnection httpURLConnection = (HttpURLConnection) hotelSearch.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            Log.d("ASYNC TASK", "FINISHED GETTING INPUTSTREAM");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            Log.d("ASYNC TASK", "FINISHED GETTING BUFFERED STREAM");
            //JSONObject object = new JSONObject(bufferedInputStream.toString());
            Log.d("ASYNC TASK", "GOT STREAM");
            byte[] content = new byte[1024];
            int byteRead = 0;

            while ((byteRead = bufferedInputStream.read(content)) != -1) {
                streamString += new String(content, 0, byteRead);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return streamString;
    }

    @Override
    protected void onPostExecute(String streamString) {


        try {
            for (int i = 0; i < 20; i++) {
                //Log.d("troubleshoot", streamString);
                JSONObject jsonObj = new JSONObject(streamString);

                JSONArray resultArray = jsonObj.getJSONArray("results");
                JSONObject hotel = resultArray.getJSONObject(i);
                // Log.d("MESSAGE FROM SERVER",strFileContents);


                //String hotelName = hotel.getString("name");
                String placeId = hotel.getString("place_id");

                Log.d("troubleshoot: This  ", placeId);
                new GetDetailsTask().execute(placeId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private class GetDetailsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String placeId = strings[0];
            String stringStream = "";

            URL placeDetail = NetworkUtils.buildPlaceDetailUrl(placeId);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) placeDetail.openConnection();
                BufferedInputStream bs = new BufferedInputStream(httpURLConnection.getInputStream());

                byte[] content = new byte[1024];
                int readByte = 0;


                while ((readByte = bs.read(content)) != -1) {
                    stringStream += new String(content, 0, readByte);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            //Bitmap hotelPhoto1 = new GetHotelPhoto().doInBackground();


            try {
                JSONObject jsonObj = new JSONObject(stringStream);
                JSONObject resultArray = jsonObj.getJSONObject("result");
                JSONArray photoArray = resultArray.getJSONArray("photos");
                String address = resultArray.getString("formatted_address");
                String name = resultArray.getString("name");
                String telephone = resultArray.getString("formatted_phone_number");
                String website = resultArray.getString("website");
                int rating = resultArray.getInt("rating");


                JSONObject photoName1 = photoArray.getJSONObject(0);
                String photoId = photoName1.getString("photo_reference");

                JSONObject photoName2 = photoArray.getJSONObject(1);
                String photoId1 = photoName2.getString("photo_reference");

                JSONObject photoName3 = photoArray.getJSONObject(2);
                String photoId2 = photoName3.getString("photo_reference");

                Bitmap hotelPhoto1 = new GetHotelPhoto().doInBackground(photoId);
                Bitmap hotelPhoto2 = new GetHotelPhoto().doInBackground(photoId1);
                Bitmap hotelPhoto3 = new GetHotelPhoto().doInBackground(photoId2);

                


                Log.d("troubleshoot: " + name, address + rating + website + telephone);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String stringStream) {


        }
    }

    void print(Bitmap bitmap) {


    }

    private class GetHotelPhoto extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... photoReferences) {
            String photoId = photoReferences[0];
            Bitmap image = null;

            URL photoUrl = NetworkUtils.buildPhotoUrl(photoId);

            try {
                HttpURLConnection htpcon = (HttpURLConnection) photoUrl.openConnection();
                BufferedInputStream bs = new BufferedInputStream(htpcon.getInputStream());

                image = BitmapFactory.decodeStream(bs);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }


}
