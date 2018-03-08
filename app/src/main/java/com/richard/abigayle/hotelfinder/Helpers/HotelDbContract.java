package com.richard.abigayle.hotelfinder.Helpers;

import android.provider.BaseColumns;

/**
 * Created by Jaruspace on 2/16/2018.
 */

public class HotelDbContract {
//    public HotelDbContract() {
//    }

    public static final class HotelListEntry implements BaseColumns{
        public static final String TABLE_NAME = "hotelist";
        public static final String COLUMN_PLACE_ID = "placeId";
        public static final String COLUMN_PLACE_RATING = "placeRating";
        public static final String COLUMN_PLACE_NAME = "placeName";
        public static final String COLUMN_PLACE_ADDRESS = "placeAd";
        public static final String COLUMN_REVIEWER_NAME = "reviewerName";
        public static final String COLUMN_REVIEWER_DP = "reviewerDp";
        public static final String COLUMN_IMAGE_ID1 = "imageId1";
        public static final String COLUMN_IMAGE_ID2 = "imageId2";
        public static final String COLUMN_IMAGE_ID3 = "imageId3";
        public static final String COLUMN_IMAGE_ID4 = "imageId4";


    }
}
