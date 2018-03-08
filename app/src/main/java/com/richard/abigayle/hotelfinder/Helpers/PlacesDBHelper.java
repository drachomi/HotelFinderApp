package com.richard.abigayle.hotelfinder.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jaruspace on 2/16/2018.
 */

public class PlacesDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "hotelist.db";
    public static final int DATABASE_VERSION = 1;


    public PlacesDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_HOTELS_TABLE = "CREATE TABLE" +
                HotelDbContract.HotelListEntry.TABLE_NAME + " (" +
                HotelDbContract.HotelListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                HotelDbContract.HotelListEntry.COLUMN_PLACE_ID + " STRING NOT NULL " +
                HotelDbContract.HotelListEntry.COLUMN_PLACE_NAME + " STRING NOT NULL" +
                HotelDbContract.HotelListEntry.COLUMN_PLACE_ADDRESS + " STRING NOT NULL" +
                HotelDbContract.HotelListEntry.COLUMN_PLACE_RATING + " STRING NOT NULL" +
                HotelDbContract.HotelListEntry.COLUMN_IMAGE_ID1 + " STRING NOT NULL" +
                HotelDbContract.HotelListEntry.COLUMN_IMAGE_ID2 + " STRING NOT NULL" +
                HotelDbContract.HotelListEntry.COLUMN_IMAGE_ID3 + " STRING NOT NULL" +
                HotelDbContract.HotelListEntry.COLUMN_IMAGE_ID4 + " STRING NOT NULL" +
                HotelDbContract.HotelListEntry.COLUMN_REVIEWER_NAME + " STRING NOT NULL" +
                HotelDbContract.HotelListEntry.COLUMN_REVIEWER_DP + "STRING NOT NULL" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_HOTELS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + HotelDbContract.HotelListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
