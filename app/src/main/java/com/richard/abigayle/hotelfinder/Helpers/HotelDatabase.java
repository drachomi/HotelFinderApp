package com.richard.abigayle.hotelfinder.Helpers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jaruspace on 2/16/2018.
 */

@Database(entities = Hotels.class,version = 1)
public abstract class HotelDatabase extends RoomDatabase {

    public abstract HotelDao hotelDao();

    private static final String DATABASE_NAME = "hotel";

    //For singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile HotelDatabase sInstance;


    public static HotelDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                if(sInstance == null){
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),HotelDatabase.class,DATABASE_NAME).build();
                }
            }

        }
        return sInstance;
    }
}
