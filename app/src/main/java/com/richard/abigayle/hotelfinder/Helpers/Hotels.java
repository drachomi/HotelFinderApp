package com.richard.abigayle.hotelfinder.Helpers;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Jaruspace on 2/16/2018.
 */


@Entity(tableName = "hotel")
public class Hotels {
        @PrimaryKey
        public int id;
        public  String  placeId;
        public  String placeName;
        public  String telephone;
        public  String placeAd;
        public String website;
        public int priceLvl;
        public  float placeRating;
        public String latLng;
        public String imageId1;
        public  String imageId2;
        public  String imageId3;
        public String distance;
        public String duration;

        public Hotels(int id, String placeId, String placeName, String telephone, String placeAd, String website, int priceLvl, float placeRating, String latLng, String imageId1, String imageId2, String imageId3,String distance,String duration) {
                this.id = id;
                this.placeId = placeId;
                this.placeName = placeName;
                this.telephone = telephone;
                this.placeAd = placeAd;
                this.website = website;
                this.priceLvl = priceLvl;
                this.placeRating = placeRating;
                this.latLng = latLng;
                this.imageId1 = imageId1;
                this.imageId2 = imageId2;
                this.imageId3 = imageId3;
                this.distance = distance;
                this.duration = duration;
        }
}
