package com.richard.abigayle.hotelfinder.Helpers;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
/**
 * Created by Jaruspace on 2/16/2018.
 */


@Entity(tableName = "hotel")
public class Hotels {
        @PrimaryKey(autoGenerate = true)
        private int id;
        private  String  placeId;
        private  String placeRating;
        private  String placeName;
        private  String placeAd;
        private  String reviewerName;
        private  String reviewerDp;
        private  String imageId1;
        private  String imageId2;
        private  String imageId3;
        private  String imageId4;

    public Hotels(int id, String placeId, String placeRating, String placeName, String placeAd, String reviewerName, String reviewerDp, String imageId1, String imageId2, String imageId3, String imageId4) {
        this.id = id;
        this.placeId = placeId;
        this.placeRating = placeRating;
        this.placeName = placeName;
        this.placeAd = placeAd;
        this.reviewerName = reviewerName;
        this.reviewerDp = reviewerDp;
        this.imageId1 = imageId1;
        this.imageId2 = imageId2;
        this.imageId3 = imageId3;
        this.imageId4 = imageId4;
    }
}
