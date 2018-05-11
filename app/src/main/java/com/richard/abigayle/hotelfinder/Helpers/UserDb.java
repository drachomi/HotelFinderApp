package com.richard.abigayle.hotelfinder.Helpers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by LENOVO on 5/10/2018.
 */
@Database(entities = User.class,version = 1)
public abstract class UserDb extends RoomDatabase {
    public abstract UserDao userDao();
    private static final String DATABASE_NAME = "user";

    //For singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile UserDb sInstance;


    public static UserDb getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                if(sInstance == null){
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),UserDb.class,DATABASE_NAME).build();
                }
            }

        }
        return sInstance;
    }
}
