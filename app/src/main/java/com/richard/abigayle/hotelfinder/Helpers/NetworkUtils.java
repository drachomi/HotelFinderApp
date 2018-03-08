package com.richard.abigayle.hotelfinder.Helpers;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jaruspace on 2/16/2018.
 */

public class NetworkUtils {

     private static  String SEARCH_BASE_URL_STRING = "https://maps.googleapis.com/maps/api/place/radarsearch/json";
    private static String SEARCH_LOCATION = "location";
    private static String SEARCH_RADIUS = "radius";
    private static String SEARCH_TYPE = "type";
    private static String SEARCH_TYPE_VALUE = "hotel";
    private static String GOOGLE_API_KEY = "key";
    private static String GOOGLE_API = "AIzaSyBWKQHS39-SYUNxEEAry1FxrMET2NwhqxE";
    private static String DETAILS_BASE_URL_STRING = "https://maps.googleapis.com/maps/api/place/details/json";
    private static String PLACE_ID = "placeid";
    private static String PHOTO_URL_STRING = "https://maps.googleapis.com/maps/api/place/photo";
    private static String MAX_WIDTH = "maxwidth";
    private static String PHOTO_REF = "photoreference";


    public static URL buildRaderUrl(String location){


        Uri buildUri = Uri.parse(SEARCH_BASE_URL_STRING).buildUpon()
                .appendQueryParameter(SEARCH_LOCATION,location)
                .appendQueryParameter(SEARCH_RADIUS,"500")
                .appendQueryParameter(SEARCH_TYPE,SEARCH_TYPE_VALUE)
                .appendQueryParameter(GOOGLE_API_KEY,GOOGLE_API)
                .build();

        URL url = null;
        try{
            url = new URL(buildUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return url;


    }
    public static URL buildPlaceDetailUrl(String placeId){
        Uri buildUri = Uri.parse(DETAILS_BASE_URL_STRING).buildUpon()
                .appendQueryParameter(PLACE_ID,placeId)
                .appendQueryParameter(GOOGLE_API_KEY,GOOGLE_API)
                .build();
        URL url = null;
        try{
            url = new URL(buildUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return url;
        
    }

    public static URL buildPhotoUrl(String photoId){
        Uri buildUri = Uri.parse(DETAILS_BASE_URL_STRING).buildUpon()
                .appendQueryParameter(MAX_WIDTH,"400")
                .appendQueryParameter(PHOTO_REF,photoId)
                .appendQueryParameter(GOOGLE_API_KEY,GOOGLE_API)
                .build();
        URL url = null;
        try{
            url = new URL(buildUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return url;

    }




}
